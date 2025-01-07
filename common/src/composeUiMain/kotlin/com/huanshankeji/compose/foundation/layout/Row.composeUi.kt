package com.huanshankeji.compose.foundation.layout

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import kotlin.jvm.JvmInline

@Composable
actual fun Row(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment: Alignment.Vertical,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.foundation.layout.Row(
        modifier.platformModifier,
        horizontalArrangement.platformValue,
        verticalAlignment.platformHorizontal,
        content.toPlatformRowScopeContent(),
    )


actual typealias PlatformRowScope = androidx.compose.foundation.layout.RowScope

//@LayoutScopeMarker
actual interface RowScope {
    actual val platformValue: PlatformRowScope

    @JvmInline
    actual value class Impl(override val platformValue: PlatformRowScope) : RowScope

    @Stable
    actual fun Modifier.weight(
        @FloatRange(from = 0.0, fromInclusive = false)
        weight: Float
    ): Modifier =
        with(platformValue) { platformModify { weight(weight) } }

    @Stable
    actual fun Modifier.align(alignment: Alignment.Vertical): Modifier =
        with(platformValue) { platformModify { align(alignment.platformHorizontal) } }
}
