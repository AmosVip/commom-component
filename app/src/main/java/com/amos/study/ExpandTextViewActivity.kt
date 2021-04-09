package com.amos.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spanned
import android.text.style.ClickableSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.TextView
import com.amos.study.databinding.ActivityTestTextviewExpandBinding
import com.amos.study.helper.StockStringBuilderHelper
import com.amos.study.utlis.TestInternal

/**
 * @author: amos
 * @date: 2021/1/20 9:53
 * @description:
 */
class ExpandTextViewActivity : BaseActivity() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, ExpandTextViewActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val MAX_LINE = 3
    private val tag = ExpandTextViewActivity.javaClass.simpleName
    private lateinit var mViewBinding:ActivityTestTextviewExpandBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_textview_expand)
        //TestInternal.testMethod()
        mViewBinding = ActivityTestTextviewExpandBinding.inflate(layoutInflater)
        mViewBinding.tvContent.setOnClickListener {
            val isExpand = mViewBinding.tvContent.tag as Boolean
            if (isExpand) {
                mViewBinding.tvContent.tag = false
                mViewBinding.tvContent.maxLines = MAX_LINE
            } else {
                val ellipsisCount = mViewBinding.tvContent.layout.getEllipsisCount(mViewBinding.tvContent.lineCount - 1)
                Log.e(tag, "ellipsisCount = $ellipsisCount")
                if (ellipsisCount > 0) {
                    mViewBinding.tvContent.setMaxHeight(getResources().getDisplayMetrics().heightPixels)
                    mViewBinding.tvContent.tag = true
                }
            }
        }

        setContent()
    }

    private fun setContent() {
        mViewBinding.tvContent.maxLines = MAX_LINE
        val content: String =
            "6月2日讯，${'$'}新华医疗(SH600587)${'$'}发布关于媒体报道的澄清公告称，经核实，剔除威士达合并报表因素影响后，公司2019年度营业收入与201" +
                    "8年度相比实际增长2.21%。公司营业收入与经营活动现金流量表匹配，不存在营业收入虚增事项。" +
                    "公司营业成本与“购买商品、接受劳务支付的现金”金额匹配，不存在营业成本虚减事项。公司不存在偿债风险。"
        mViewBinding.tvContent.setText(
            StockStringBuilderHelper.setSpan(
                this,
                content
            )/*, TextView.BufferType.SPANNABLE*/
        )
//        tvContent.movementMethod = LinkMovementMethod.getInstance()
        //tvContent.movementMethod = StockStringBuilderHelper.CustomLinkMovementMethod.getInstance()
        //tvContent.setTextIsSelectable(true)
        mViewBinding.tvContent.tag = false
        mViewBinding.tvContent.setOnTouchListener(ClickableSpanTouchListener())
    }

    class ClickableSpanTouchListener : OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            if (v !is TextView) {
                return false
            }
            val widget = v
            val text = widget.text as? Spanned ?: return false
            val action = event.action
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
                val index = getTouchedIndex(widget, event)
                val link =
                    getClickableSpanByIndex(widget, index)
                if (link != null) {
                    if (action == MotionEvent.ACTION_UP) {
                        link.onClick(widget)
                    }
                    return true
                }
            }
            return false
        }

        companion object {
            fun getClickableSpanByIndex(widget: TextView?, index: Int): ClickableSpan? {
                if (widget == null || index < 0) {
                    return null
                }
                val charSequence = widget.text as? Spanned ?: return null
                // end 应该是 index + 1，如果也是 index，得到的结果会往左偏
                val links = charSequence.getSpans(
                    index, index + 1,
                    ClickableSpan::class.java
                )
                return if (links != null && links.size > 0) {
                    links[0]
                } else null
            }

            fun getTouchedIndex(widget: TextView?, event: MotionEvent?): Int {
                if (widget == null || event == null) {
                    return -1
                }
                var x = event.x.toInt()
                var y = event.y.toInt()
                x -= widget.totalPaddingLeft
                y -= widget.totalPaddingTop
                x += widget.scrollX
                y += widget.scrollY
                val layout = widget.layout
                // 根据 y 得到对应的行 line
                val line = layout.getLineForVertical(y)
                // 判断得到的 line 是否正确
                if (x < layout.getLineLeft(line) || x > layout.getLineRight(line) || y < layout.getLineTop(
                        line
                    ) || y > layout.getLineBottom(line)
                ) {
                    return -1
                }
                // 根据 line 和 x 得到对应的下标
                var index = layout.getOffsetForHorizontal(line, x.toFloat())
                // 这里考虑省略号的问题，得到真实显示的字符串的长度，超过就返回 -1
                val showedCount = widget.text.length - layout.getEllipsisCount(line)
                if (index > showedCount) {
                    return -1
                }
                // getOffsetForHorizontal 获得的下标会往右偏
                // 获得下标处字符左边的左边，如果大于点击的 x，就可能点的是前一个字符
                if (layout.getPrimaryHorizontal(index) > x) {
                    index -= 1
                }
                return index
            }
        }
    }
}