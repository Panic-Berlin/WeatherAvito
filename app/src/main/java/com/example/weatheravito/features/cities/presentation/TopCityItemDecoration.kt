package com.example.weatheravito.features.cities.presentation

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class TopCityItemDecoration(
    context: Context,
    @DimenRes leftAndRightSpacing: Int,
    @DimenRes topAndBottomSpacing: Int
) : RecyclerView.ItemDecoration() {

    private val leftAndRightSpacing = context.resources.getDimensionPixelSize(leftAndRightSpacing)
    private val topAndBottomSpacing = context.resources.getDimensionPixelSize(topAndBottomSpacing)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = topAndBottomSpacing
        outRect.left = leftAndRightSpacing
        outRect.right = leftAndRightSpacing
    }
}