package com.huanshankeji.compose.foundation.layout

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
)


expect interface PlatformRowScope

//@LayoutScopeMarker
expect interface RowScope {
    val platformValue: PlatformRowScope

    value class Impl(override val platformValue: PlatformRowScope) : RowScope

    @Stable
    open fun Modifier.weight(
        @FloatRange(from = 0.0, fromInclusive = false)
        weight: Float
    ): Modifier

    @Stable
    open fun Modifier.align(alignment: Alignment.Vertical): Modifier
}

fun (@Composable (RowScope.() -> Unit)).toPlatformRowScopeContent(): @Composable PlatformRowScope.() -> Unit =
    { RowScope.Impl(this).this@toPlatformRowScopeContent() }
