package com.amos.study.widget

import android.content.Context
import android.content.res.Resources
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatTextView
import com.amos.study.R

/**
 * @author: amos
 * @date: 2021/3/4 14:15
 * @description: 查看更多text view
 */
class SeeDetailTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    private var originText: String? = null // 原始内容文本
    private var initWidth = 0 // TextView可展示宽度
    private var mMaxLines = 2 // TextView最大行数
    private var SPAN_END: SpannableString? = null
    private var mDefaultEndWord = "查看详情"
    private var mCallBack: OnCallBack? = null

    init {
        this.setOnTouchListener { v, event ->
            if (v !is TextView) {
                false
            }
            val widget: TextView = v as TextView
            val text = widget.text as? Spanned ?: false
            val action = event.action
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
                val index = getTouchedIndex(widget, event)
                val link = getClickableSpanByIndex(widget, index)
                if (link != null) {
                    if (action == MotionEvent.ACTION_UP) {
                        link.onClick(widget)
                    }
                    true
                }
            }
            false
        }
        this.tag = false

        this.setOnClickListener {
            mCallBack?.onClick()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        initWidth = measuredWidth
    }

    /**
     * 设置要追加的文案
     * 例如: 全文 | 查看更多
     */
    fun setEndWord(endWord: String): SeeDetailTextView {
        mDefaultEndWord = endWord
        return this
    }

    /**
     * 设置文案
     * 注意: 不要使用 textView 的 setText
     */
    fun setTextWord(content: CharSequence?) {
        originText = content?.toString() ?: ""
        post { setSeeDefaultText() }
    }

    fun setCallBack(callBack: OnCallBack?) {
        mCallBack = callBack
    }

    /**
     * 设置TextView可显示的最大行数
     *
     * @param maxLines 最大行数
     */
    override fun setMaxLines(maxLines: Int) {
        mMaxLines = maxLines
        super.setMaxLines(maxLines)
    }

    /**
     * 查看详情扩展文案
     */
    private fun initEndWordSpan() {
        val content = mDefaultEndWord
        SPAN_END = SpannableString(content)
        val span = ButtonSpan(context, textSize, OnClickListener { //点击回调
            mCallBack?.onSpanClick()
        })
        SPAN_END!!.setSpan(span, 0, content.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    /**
     * 设置查看详情文案
     */
    private fun setSeeDefaultText() {
        if (SPAN_END == null) {
            initEndWordSpan()
        }
        val maxLines = maxLines
        var workingText = originText
        if (maxLines != -1) {
            val layout = createWorkingLayout(workingText)
            if (layout.lineCount > maxLines) {
                //获取一行显示字符个数，然后截取字符串数
                workingText = originText!!.substring(0, layout.getLineEnd(maxLines - 1)).trim { it <= ' ' } // 收起状态原始文本截取展示的部分
                val showText = originText!!.substring(0, layout.getLineEnd(maxLines - 1)).trim { it <= ' ' } + "..." + SPAN_END
                var layout2 = createWorkingLayout(showText)
                // 对workingText进行-1截取，直到展示行数==最大行数，并且添加 SPAN_CLOSE 后刚好占满最后一行
                while (layout2.lineCount > maxLines) {
                    val lastSpace = workingText!!.length - 1
                    if (lastSpace == -1) {
                        break
                    }
                    workingText = workingText.substring(0, lastSpace)
                    layout2 = createWorkingLayout("$workingText...$SPAN_END")
                }
                workingText = "$workingText..."
            } else { //如果不大于 就用空格填充
                val showText = "$originText...$SPAN_END"
                var layout2 = createWorkingLayout(showText)
                val currentLintCount = layout2.lineCount
                //如果追加了
                if (currentLintCount > maxLines) {
                    while (layout2.lineCount > maxLines) {
                        val lastSpace = workingText!!.length - 1
                        if (lastSpace == -1) {
                            break
                        }
                        workingText = workingText.substring(0, lastSpace)
                        layout2 = createWorkingLayout("$workingText...$SPAN_END")
                    }
                }
                workingText = "$workingText..."
            }
        }
        text = workingText
        //必须使用append，不能在上面使用+连接，否则spannable会无效
        append(SPAN_END)
    }

    //返回textview的显示区域的layout，该textview的layout并不会显示出来，只是用其宽度来比较要显示的文字是否过长
    private fun createWorkingLayout(workingText: String?): Layout {
        return StaticLayout(workingText, paint, initWidth - paddingLeft - paddingRight,
                Layout.Alignment.ALIGN_NORMAL, lineSpacingMultiplier, lineSpacingExtra, false)
    }

    private class ButtonSpan @JvmOverloads constructor(private val context: Context, private val textSizePx: Float, var onClickListener: OnClickListener?, @param:ColorRes private val colorId: Int = R.color.color_E03C34) : ClickableSpan() {
        override fun updateDrawState(ds: TextPaint) {
            ds.color = context.resources.getColor(colorId)
            //ds.setTextSize(dip2px(16));
            ds.textSize = textSizePx
            ds.isUnderlineText = false
        }

        override fun onClick(widget: View) {
            if (onClickListener != null) {
                onClickListener!!.onClick(widget)
            }
        }

        companion object {
            fun dip2px(dipValue: Float): Int {
                val scale = Resources.getSystem().displayMetrics.density
                return (dipValue * scale + 0.5f).toInt()
            }
        }

    }


    private fun getTouchedIndex(widget: TextView?, event: MotionEvent?): Int {
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
        if (x < layout.getLineLeft(line) || x > layout.getLineRight(line) || y < layout.getLineTop(line) || y > layout.getLineBottom(line)
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

    private fun getClickableSpanByIndex(widget: TextView?, index: Int): ClickableSpan? {
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

    interface OnCallBack {
        fun onSpanClick();
        fun onClick()
    }
}