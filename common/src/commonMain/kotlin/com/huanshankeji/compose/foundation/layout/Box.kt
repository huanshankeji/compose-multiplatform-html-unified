package com.huanshankeji.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun Box(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
)

@Composable
expect fun Box(modifier: Modifier)

//@LayoutScopeMarker
@Immutable
expect interface BoxScope {
    @Stable
    open fun Modifier.align(alignment: Alignment): Modifier
}
