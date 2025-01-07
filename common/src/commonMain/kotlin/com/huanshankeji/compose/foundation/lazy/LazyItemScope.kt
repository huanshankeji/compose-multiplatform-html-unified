package com.huanshankeji.compose.foundation.lazy

import androidx.annotation.FloatRange
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier

@Stable
/*
@LazyScopeMarker
@JvmDefaultWithCompatibility
*/
expect class LazyItemScope {
    fun Modifier.fillParentMaxSize(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float = 1f
    ): Modifier

    fun Modifier.fillParentMaxWidth(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float = 1f
    ): Modifier

    fun Modifier.fillParentMaxHeight(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float = 1f
    ): Modifier

    /*
    @ExperimentalFoundationApi
    fun Modifier.animateItemPlacement(
        animationSpec: FiniteAnimationSpec<IntOffset> = spring(
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = androidx.compose.ui.unit.IntOffset.VisibilityThreshold
        )
    ): Modifier
    */
}
