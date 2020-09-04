package com.amos.study

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * <pre>
 *     author : amos
 *     time   : 2020/08/17 00:31
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CommonItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val totalCount = parent.adapter?.itemCount ?: 0
        val position = parent.getChildAdapterPosition(view)
        val layoutManager = parent.layoutManager as GridLayoutManager
        val span = layoutManager.spanCount
        val left = 0
        val top = 2
        val right = if (position % span < span - 1) 2 else 0
        val bottom = if (position >= (totalCount / span * span)) 2 else 0
        outRect.set(left, top, right, bottom)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val paint = Paint()
        paint.color = Color.parseColor("#03DAC5")
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 2f

        val childCount = parent.childCount
        val layoutManager = parent.layoutManager as GridLayoutManager
        val span = layoutManager.spanCount
        for (index in 0 until childCount) {
            val view = parent.getChildAt(index);
            val viewPosition = parent.getChildAdapterPosition(view)
            //顶部线
            val topRect = Rect()
            topRect.left = view.left
            topRect.top = view.top - 2
            topRect.right = view.right
            topRect.bottom = view.top
            c.drawRect(topRect, paint)
            //中间线
            if (viewPosition % span < span - 1) {
                val centerRect = Rect()
                centerRect.left = view.right
                centerRect.top = view.top
                centerRect.right = view.right + 2
                centerRect.bottom = view.bottom + 2
                c.drawRect(centerRect, paint)
            }

            //最后一行底部线
            val totalCount = parent.adapter?.itemCount ?: 0
            if (viewPosition >= (totalCount / span) * span) {
                val bottomRect = Rect()
                bottomRect.left = view.left
                bottomRect.top = view.bottom
                bottomRect.right = view.right
                bottomRect.bottom = view.bottom + 2
                c.drawRect(bottomRect, paint)
            }
        }
    }
}