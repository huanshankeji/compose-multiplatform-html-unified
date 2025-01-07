package com.huanshankeji.compose.foundation.lazy

import androidx.annotation.FloatRange
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyItemScope as PlatformLazyItemScope

@Stable
@LazyScopeMarker
//@JvmDefaultWithCompatibility
actual class LazyItemScope(val platformValue: PlatformLazyItemScope) {
    actual fun Modifier.fillParentMaxSize(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float
    ): Modifier =
        platformModify { with(platformValue) { fillParentMaxSize(fraction) } }

    actual fun Modifier.fillParentMaxWidth(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float
    ): Modifier =
        platformModify { with(platformValue) { fillParentMaxWidth(fraction) } }

    actual fun Modifier.fillParentMaxHeight(
        @FloatRange(from = 0.0, to = 1.0)
        fraction: Float
    ): Modifier =
        platformModify { with(platformValue) { fillParentMaxHeight(fraction) } }
}