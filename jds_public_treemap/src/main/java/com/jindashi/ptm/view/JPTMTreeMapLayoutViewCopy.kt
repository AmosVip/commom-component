package com.jindashi.ptm.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import com.jindashi.ptm.treemap.JPTMIItemContract
import com.jindashi.ptm.treemap.JPTMNewSquarifiedLayout
import com.jindashi.ptm.treemap.JPTMTreeMapItem
import com.jindashi.ptm.treemap.Rect
import com.jindashi.ptm.utils.TreeMapColorUtil
import com.jindashi.ptm.utils.ViewUtils
import kotlin.math.abs
import kotlin.math.max

/**
 * 云图绘制 view
 */
class JPTMTreeMapLayoutViewCopy @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), JPTMITreeMapViewContract {
    private var mMapLayout: JPTMNewSquarifiedLayout? = null
    private var mappableItems: ArrayList<JPTMIItemContract> = ArrayList()
    private var mRectBackgroundPaint: Paint? = null
    private var mRectBorderPaint: Paint? = null
    private var mTextPaint: Paint? = null
    private var mPricePaint: Paint? = null
    var curX = 0
    var curY = 0
    private var mMaxWidth: Int = 0
    private var mMaxHeight: Int = 0
    private var mCacheAdapter: JPTMTreeMapAdapter? = null
    private var mUpDownFloatBoundaryMaxValue: Double = 0.0  //存储涨跌幅的最大边界
    private var mCachePositionMap: MutableMap<Int, JPTMIItemContract> = mutableMapOf()
    private var mTouchSlop: Int = 0
    private var canClick = false
    private var isMoving: Boolean = false

    init {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        mMapLayout = JPTMNewSquarifiedLayout()
        initPaint()
    }


    /**
     * 刷新单个数据
     */
    override fun notifyItemChanged(position: Int) {
        mCacheAdapter?.let {
            if (mCachePositionMap.containsKey(position)) {
                val item = mCachePositionMap[position] as JPTMTreeMapItem
                val type = it.getType()
                //涨跌幅的更改
                val upDownWord = it.getUpDownValueWord(type, position)
                val upDownValue = it.getUpDownValue(type, position)
                mUpDownFloatBoundaryMaxValue = it.getUpDownBoundaryMaxValue()
                //背景颜色更改
                val bgColor: String = TreeMapColorUtil.getTargetColor(
                    upDownValue,
                    mUpDownFloatBoundaryMaxValue,
                    -(mUpDownFloatBoundaryMaxValue)
                )
                item.upDownFloatWord = upDownWord
                item.bgColor = Color.parseColor(bgColor)
                invalidate()
            }
        }
        //initModel()
    }

    override fun notifyDataSetChanged() {
        initModel()
    }

    /**
     * 设置数据的adapter
     */
    fun <A : JPTMTreeMapAdapter> setAdapter(adapter: A) {
        if (adapter.getSize() == 0) {
            return
        }
        mCacheAdapter = adapter
        mCacheAdapter?.registerAdapterDataObserver(this)
        initModel()
    }

    /**
     * 涨跌幅边界最大值
     */
    fun getUpDownBoundaryMaxValue(): Double {
        return mUpDownFloatBoundaryMaxValue
    }

    private fun initModel() {
        mCacheAdapter?.let {
            mappableItems.clear()
            mCachePositionMap.clear()
            mUpDownFloatBoundaryMaxValue = it.getUpDownBoundaryMaxValue() //涨跌幅区间计算
            for (index in 0 until it.getSize()) {
                val type = it.getType()  //类型'
                val calculateValue = it.getCalculateAreaOfValue(type, index) //计算面积需要的值
                val name = it.getName(type, index) // 标题文案
                val upDownWord = it.getUpDownValueWord(type, index) //涨跌幅文案
                val fundsWord = it.getFundsWord(type, index) // 资金文案 如: 净流入 70.6亿

                val upDownValue = it.getUpDownValue(type, index) * 100

                var bgColor: String = TreeMapColorUtil.getTargetColor(
                    upDownValue,
                    mUpDownFloatBoundaryMaxValue,
                    -(mUpDownFloatBoundaryMaxValue)
                )

                val item = JPTMTreeMapItem(calculateValue, name, fundsWord, upDownWord, index)
                item.bgColor = Color.parseColor(bgColor)
                mappableItems.add(item)
                mCachePositionMap.put(index, item)
            }

            mMapLayout?.layout(
                mappableItems,
                Rect(0.0, 0.0, mMaxWidth.toDouble(), mMaxHeight.toDouble())
            )

            invalidate()
        }
    }

    private fun initPaint() {
        // Set up the Paint for the rectangle background
        mRectBackgroundPaint = Paint().apply {
            setColor(Color.CYAN)
            setStyle(Paint.Style.FILL)
        }


        // Set up the Paint for the rectangle border
        mRectBorderPaint = Paint().apply {
            setColor(Color.WHITE)
            setStyle(Paint.Style.STROKE)// outline the rectangle
            setStrokeWidth(2f) // single-pixel outline
        }


        // Set up the Paint for the text label
        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            setColor(Color.WHITE)
            setTextSize(ViewUtils.sp2px(context.resources, 16f))
            setFakeBoldText(true)
        }


        mPricePaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            setColor(Color.WHITE)
            setTextSize(ViewUtils.sp2px(context.resources, 12f))
        }


    }

    public override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Lay out the placement of the rectangles within the area available to this view
        //mapLayout?.layout(mappableItems, Rect(0.0, 0.0, w.toDouble(), h.toDouble()))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mMaxWidth = measuredWidth
        mMaxHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mappableItems?.let {
            for (i in it.indices) {
                val item = it[i] as JPTMTreeMapItem
                mRectBackgroundPaint!!.color = item.bgColor
                drawRectangle(canvas, item.boundsRectF)
                drawText(canvas, item.title, item.fundsWord, item.upDownFloatWord, item.boundsRectF)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                canClick = true
                isMoving = false
                curX = event.x.toInt()
                curY = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = event.x - curX
                val offsetY = event.y - curY
                if (abs(offsetX) > mTouchSlop || abs(offsetY) > mTouchSlop || isMoving) {
                    parent.requestDisallowInterceptTouchEvent(false)
                    canClick = false
                    isMoving = true
                    return false
                } else {
                    parent.requestDisallowInterceptTouchEvent(true)
                    canClick = true
                    return true
                }
                /*if (Math.abs(Math.abs(event.x) - curX) > 70 || Math.abs(
                        Math.abs(
                            event.y
                        ) - curY
                    ) > 70
                ) {
                    canClick = false
                }*/
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (canClick) {
                    findRect(curX, curY)
                    canClick = false
                    return true
                } else {
                    return false
                }
            }
        }
        return true
    }


    private fun findRect(x: Int, y: Int) {

        mappableItems.let {
            for (i in it.indices) {
                val item = it[i] as JPTMTreeMapItem
                val bounds = item.boundsRectF
                bounds?.let {
                    if (x > it.left && x < it.right && y > it.top && y < it.bottom) {
                        mCacheAdapter?.let {
                            it.onItemClick(it.getType(), item.sourceDataPosition)
                        }
                    }
                }

            }
        }

    }

    private fun drawRectangle(canvas: Canvas, rectF: RectF) {
        canvas.drawRect(rectF, mRectBackgroundPaint!!)
        canvas.drawRect(rectF, mRectBorderPaint!!)
    }

    private fun drawText(
        canvas: Canvas, title: String, fundsWord: String,
        upDownWord: String, rectF: RectF
    ) {

        mTextPaint?.let {
            val textSize = Math.min(rectF.width() / 6, 50f)
            mTextPaint!!.textSize = textSize
            val centerY = rectF.top + rectF.height() / 2
            val strWidth = it.measureText(title)
            //title
            canvas.drawText(title, rectF.left + rectF.width() / 2 - strWidth / 2, centerY, it)


            mPricePaint?.let {
                val textSize1 = Math.min(rectF.width() / 10, 35f)
                it.textSize = textSize1
                val strWidth1 = it.measureText(fundsWord)
                //资金文案
                canvas.drawText(
                    fundsWord, rectF.left + rectF.width() / 2 - strWidth1 / 2,
                    centerY + textSize1 + ViewUtils.dp2px(resources, 3f), it
                )
                //涨跌幅文案
                val upDownWordWith = it.measureText(upDownWord)
                canvas.drawText(
                    upDownWord, rectF.left + rectF.width() / 2 - upDownWordWith / 2,
                    centerY + textSize1 * 2 + ViewUtils.dp2px(resources, 3f) * 2, it
                )
            }
        }
    }

    abstract class JPTMTreeMapAdapter {
        private var mCallBack: JPTMITreeMapViewContract? = null

        /**
         * 列表大小
         */
        abstract fun getSize(): Int

        /**
         * 面积计算的值
         */
        abstract fun getCalculateAreaOfValue(type: Int, position: Int): Double

        /**
         * 获取文案 例如资金金流入:73.6亿
         */
        abstract fun getFundsWord(type: Int, position: Int): String

        /**
         * 涨跌幅
         */
        abstract fun getUpDownValue(type: Int, position: Int): Double

        /**
         * 获取涨跌幅文案 例如: 涨跌幅 +4.6%
         */
        abstract fun getUpDownValueWord(type: Int, position: Int): String

        /**
         * 股票名称
         */
        abstract fun getName(type: Int, position: Int): String


        /**
         *  item 点击事件处理
         */
        abstract fun onItemClick(type: Int, position: Int)

        /**
         * 自定义类型
         */
        open fun getType(): Int { //当前的类型
            return 0
        }

        /**
         * 数据列表更改刷新view
         */
        open fun notifyDataSetChanged() {
            mCallBack?.notifyDataSetChanged()
        }


        /**
         * 刷新某一个item的数据 针对动态修改涨跌幅
         */
        open fun notifyItemChanged(position: Int) {
            mCallBack?.notifyItemChanged(position)
        }

        /**
         * 注册监听
         */
        fun registerAdapterDataObserver(callBack: JPTMITreeMapViewContract) {
            mCallBack = callBack
        }

        /**
         * 获取涨跌区间最大值 用于颜色指示条 和 涨跌颜色
         */
        open fun getUpDownBoundaryMaxValue(): Double {
            var maxBoundary = 0.0
            val type = getType()
            for (index in 0 until getSize()) {
                val upDown = getUpDownValue(type, index)
                maxBoundary = max(maxBoundary, abs(upDown))
            }
            return maxBoundary
        }
    }
}