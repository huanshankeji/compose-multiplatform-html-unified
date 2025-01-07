package com.huanshankeji.compose.foundation.layout

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import kotlin.jvm.JvmInline

@Composable
actual fun Column(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    content: @Composable ColumnScope.() -> Unit
) =
    androidx.compose.foundation.layout.Column(
        modifier.platformModifier,
        verticalArrangement.platformValue,
        horizontalAlignment.platformHorizontal,
        content.toCommonColumnScopeContent()
    )


actual typealias PlatformColumnScope = androidx.compose.foundation.layout.ColumnScope

//@LayoutScopeMarker
actual interface ColumnScope {
    actual val platformValue: PlatformColumnScope

    @JvmInline
    actual value class Impl(override val platformValue: PlatformColumnScope) : ColumnScope

    @Stable
    actual fun Modifier.weight(
        @FloatRange(from = 0.0, fromInclusive = false)
        weight: Float
    ): Modifier =
        with(platformValue) { platformModify { weight(weight) } }

    @Stable
    actual fun Modifier.align(alignment: Alignment.Horizontal): Modifier =
        with(platformValue) { platformModify { align(alignment.platformHorizontal) } }
}
