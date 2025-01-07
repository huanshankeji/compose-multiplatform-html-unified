package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.ExperimentalFoundationApi
import com.huanshankeji.compose.foundation.layout.BoxScope
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

// copied and adapted from `androidx.compose.foundation.layout`

@ExperimentalFoundationApi
@Composable
//@UiComposable
expect fun BoxWithConstraints(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    //propagateMinConstraints: Boolean = false,
    content: @Composable /*@UiComposable*/ BoxWithConstraintsScope.() -> Unit
)

@Stable
interface BoxWithConstraintsScope : BoxScope {
    val maxWidth: Dp
    val maxHeight: Dp
}
