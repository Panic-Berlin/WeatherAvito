package com.example.weatheravito.features.temperature.presentation

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class DayWeatherItemDecoration(context: Context, @DimenRes leftAndRightSpacing: Int, @DimenRes topAndBottomSpacing: Int, @DimenRes bottomSpacing: Int) :
    RecyclerView.ItemDecoration() {
    private val leftAndRightSpacing = context.resources.getDimensionPixelSize(leftAndRightSpacing)
    private val topAndBottomSpacing = context.resources.getDimensionPixelSize(topAndBottomSpacing)
    private val bottomSpacing = context.resources.getDimensionPixelSize(bottomSpacing)



    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val viewHolder = parent.getChildViewHolder(view)
        if (viewHolder.layoutPosition == 0) {
            outRect.left = leftAndRightSpacing
        }
        outRect.top = topAndBottomSpacing
        outRect.bottom = bottomSpacing
        outRect.right = leftAndRightSpacing

    }
}