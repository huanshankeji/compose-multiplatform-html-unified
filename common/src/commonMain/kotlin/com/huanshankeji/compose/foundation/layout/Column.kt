package com.huanshankeji.compose.foundation.layout

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import kotlin.jvm.JvmInline

@Composable
expect fun Column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
)


expect interface PlatformColumnScope

//@LayoutScopeMarker
expect interface ColumnScope {
    val platformValue: PlatformColumnScope

    @JvmInline
    value class Impl(override val platformValue: PlatformColumnScope) : ColumnScope

    @Stable
    open fun Modifier.weight(
        @FloatRange(from = 0.0, fromInclusive = false)
        weight: Float
    ): Modifier

    @Stable
    open fun Modifier.align(alignment: Alignment.Horizontal): Modifier
}

fun (@Composable (ColumnScope.() -> Unit)).toCommonColumnScopeContent(): @Composable PlatformColumnScope.() -> Unit =
    { ColumnScope.Impl(this).this@toCommonColumnScopeContent() }
