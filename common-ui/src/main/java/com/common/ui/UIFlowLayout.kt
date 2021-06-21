package com.common.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * <pre>
 * author : amos
 * time   : 2020/08/01 19:10
 * desc   : 流式布局
 * version: 1.0
</pre> *
 */
class UIFlowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes), IUIFlowLayoutContract {
    private var mAdapter: UIFlowAdapter? = null //标签适配器
    private val mRowViewList: MutableList<MutableList<View>> = mutableListOf() //记录每一行的view
    private val mRowHeightList: MutableList<Int> = ArrayList() //记录每一行的行高
    private var rowGap = dp2px(10f) //每行之间的间距
    private var columnGap = dp2px(10f) //每列之间的间距
    private var maxRow = 0 //能够显示的最大行数  ( maxRow <= 0 不做限制)

    /**
     * setMaxRow 设置最大显示的行数
     * setRowGap  设置行间距
     * setColumnGap 设置列间距
     * setUIFlowAdapter  设置适配器
     * notifyDataSetChanged  刷新数据
     */
    fun setMaxRow(maxRow: Int): UIFlowLayout = apply {
        this.maxRow = maxRow
    }

    fun setRowGap(rowGap: Int): UIFlowLayout = apply {
        this.rowGap = if (rowGap < 0) 0 else rowGap
    }

    fun setColumnGap(columnGap: Int): UIFlowLayout = apply {
        this.columnGap = if (columnGap < 0) 0 else columnGap
    }

    /**
     * 设置标签适配器
     */
    fun setUIFlowAdapter(adapter: UIFlowAdapter) {
        mAdapter = adapter
        mAdapter!!.registerListener(this)
        parsing()
    }

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.UIFlowLayout)
            if (attributes.hasValue(R.styleable.UIFlowLayout_ui_flow_row_gap)) {
                rowGap = attributes.getDimensionPixelOffset(
                    R.styleable.UIFlowLayout_ui_flow_row_gap,
                    rowGap
                )
            }
            if (attributes.hasValue(R.styleable.UIFlowLayout_ui_flow_column_gap)) {
                columnGap = attributes.getDimensionPixelOffset(
                    R.styleable.UIFlowLayout_ui_flow_column_gap,
                    columnGap
                )
            }
            if (attributes.hasValue(R.styleable.UIFlowLayout_ui_flow_max_row_count)) {
                maxRow = attributes.getInt(R.styleable.UIFlowLayout_ui_flow_max_row_count, maxRow)
            }
            attributes.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (childCount == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        //父容器能够给到的最大宽
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        //父容器的测量模式
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        //父容器能够给到的最大高
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        //父容器的测量模式
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        //每一行的最大宽度
        var rowMaxWidth = 0
        //每一行的最大高度
        var rowMaxHeight = 0
        //总的最大高度
        var totalRowHeight = 0
        //总的最大宽度
        var totalRowWidth = 0
        //每个子view 的 宽和高
        var childWidth = 0

        var childHeight = 0
        mRowViewList.clear()
        mRowHeightList.clear()
        var rowChildView: MutableList<View> = ArrayList()
        var columnGapIndex = 0 //
        var i = 0
        val count = childCount
        while (i < count) {
            val childView = getChildAt(i)
            val params = childView.layoutParams as MarginLayoutParams
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0)
            childWidth = params.leftMargin + params.rightMargin + childView.measuredWidth
            childHeight = params.topMargin + params.bottomMargin + childView.measuredHeight
            if (rowMaxWidth + childWidth > widthSize - paddingLeft - paddingRight - columnGapIndex * columnGap) { //超过一行
                totalRowWidth = Math.max(rowMaxWidth, totalRowWidth)
                //totalRowHeight += rowMaxHeight;
                mRowViewList.add(rowChildView)
                mRowHeightList.add(rowMaxHeight)
                rowChildView = ArrayList()
                rowMaxWidth = childWidth
                rowMaxHeight = childHeight
                columnGapIndex = 0
            } else { //未超过一行
                rowMaxWidth += childWidth + columnGapIndex * columnGap
                rowMaxHeight = Math.max(rowMaxHeight, childHeight)
                columnGapIndex++
            }
            rowChildView.add(childView)
            if (i == count - 1) { //最后一个
                mRowViewList.add(rowChildView)
                mRowHeightList.add(rowMaxHeight)
                totalRowWidth = Math.max(totalRowWidth, rowMaxWidth)
            }
            i++
        }
        val rowMax: Int
        rowMax = if (maxRow <= 0) { //不限制行数
            mRowHeightList.size
        } else { //限制行数
            Math.min(maxRow, mRowHeightList.size)
        }
        for (rowIndex in 0 until rowMax) {
            totalRowHeight += mRowHeightList[rowIndex]
        }
        if (rowMax > 0) {
            totalRowHeight += (rowMax - 1) * rowGap
        }
        //针对受限制的view 做一个处理
        if (rowMax < mRowViewList.size) {
            for (index in rowMax until mRowViewList.size) {
                val tempList = mRowViewList[index]
                tempList.forEach {
                    //it.visibility = GONE
                    removeView(it)
                }
            }
        }
        val resultWidth = if (widthMode == MeasureSpec.EXACTLY) widthSize else totalRowWidth
        val resultHeight = if (heightMode == MeasureSpec.EXACTLY) heightSize else totalRowHeight
        setMeasuredDimension(resultWidth, resultHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (mRowViewList == null || mRowViewList.size == 0) {
            return
        }
        var left: Int
        var top: Int
        var right: Int
        var bottom: Int
        var contentLeft = paddingLeft
        var contentTop = paddingTop
        val rowCount = if (maxRow <= 0) mRowViewList.size else maxRow
        var i = 0
        val count = mRowViewList.size
        while (i < count && i < rowCount) {
            val childView = mRowViewList[i]
            for (view in childView) {
                val params = view.layoutParams as MarginLayoutParams
                left = contentLeft + params.leftMargin
                top = contentTop + params.topMargin
                right = left + view.measuredWidth
                bottom = top + view.measuredHeight
                view.layout(left, top, right, bottom)
                contentLeft = right + params.rightMargin + columnGap
            }
            contentLeft = paddingLeft
            contentTop += mRowHeightList[i] + rowGap
            i++
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
    }

    override fun notifyDataSetChanged() {
        parsing()
        this.requestLayout()
    }

    /**
     * 解析数据
     */
    private fun parsing() {
        this.removeAllViews()
        mAdapter?.let {
            if (it.getSize() > 0) {
                for (index in 0 until it.getSize()) {
                    val labelView = it.getView(context, index)
                    labelView.setOnClickListener { view ->
                        it.onLabelViewClick(view, index)
                    }
                    this.addView(labelView)
                }
            }
        }
    }

    private fun dp2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 动态标签的适配器
     */
    abstract class UIFlowAdapter {
        private var mObserver: IUIFlowLayoutContract? = null

        fun registerListener(listener: IUIFlowLayoutContract) {
            mObserver = listener
        }


        abstract fun getSize(): Int
        abstract fun getView(
            context: Context,
            position: Int
        ): View

        open fun onLabelViewClick(view: View, position: Int) {

        }

        open fun notifyDataSetChanged() {
            mObserver?.notifyDataSetChanged()
        }
    }
}