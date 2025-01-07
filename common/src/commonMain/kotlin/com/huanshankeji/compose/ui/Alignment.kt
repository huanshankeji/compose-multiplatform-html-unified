package com.huanshankeji.compose.ui

import androidx.compose.runtime.Stable

// TODO use `@Immutable` instead?
@Stable
expect interface Alignment {
    @Stable
    interface Horizontal

    @Stable
    interface Vertical

    companion object {
        // 2D Alignments.
        @Stable
        val TopStart: Alignment

        @Stable
        val TopCenter: Alignment

        @Stable
        val TopEnd: Alignment

        @Stable
        val CenterStart: Alignment

        @Stable
        val Center: Alignment

        @Stable
        val CenterEnd: Alignment

        @Stable
        val BottomStart: Alignment

        @Stable
        val BottomCenter: Alignment

        @Stable
        val BottomEnd: Alignment

        // 1D Alignment.Verticals.
        @Stable
        val Top: Vertical

        @Stable
        val CenterVertically: Vertical

        @Stable
        val Bottom: Vertical

        // 1D Alignment.Horizontals.
        @Stable
        val Start: Horizontal

        @Stable
        val CenterHorizontally: Horizontal

        @Stable
        val End: Horizontal
    }
}