package com.amos.study.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout

/**
 * @author: amos
 * @date: 2021/6/16 13:46
 * @description:
 */
class ViewVisibleWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        logE("onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        logE("onDetachedFromWindow")
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        logE("onVisibilityChanged  changedView = ${changedView.toString()}  visible = $visibility")
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        logE("onWindowVisibilityChanged = $visibility")
    }

    override fun onWindowSystemUiVisibilityChanged(visible: Int) {
        super.onWindowSystemUiVisibilityChanged(visible)
        logE("onWindowSystemUiVisibilityChanged  = $visible")
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        logE("onWindowFocusChanged  = $hasWindowFocus")
    }

    override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
    }

    private fun logE(content: String) {
        Log.e("ViewVisibleWidget", content)
    }
}