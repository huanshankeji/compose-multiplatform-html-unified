package com.huanshankeji.compose.foundation.lazy

import androidx.annotation.FloatRange
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.foundation.layout.fillMaxHeight
import com.huanshankeji.compose.foundation.layout.fillMaxSize
import com.huanshankeji.compose.foundation.layout.fillMaxWidth
import com.huanshankeji.compose.ui.Modifier

@Stable
/*
@LazyScopeMarker
@JvmDefaultWithCompatibility
*/
actual object LazyItemScope {
    // I am not sure whether these implementations work perfectly.

    actual fun Modifier.fillParentMaxSize(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float
    ): Modifier =
        fillMaxSize(fraction)

    actual fun Modifier.fillParentMaxWidth(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float
    ): Modifier =
        fillMaxWidth(fraction)

    actual fun Modifier.fillParentMaxHeight(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float
    ): Modifier =
        fillMaxHeight(fraction)
}