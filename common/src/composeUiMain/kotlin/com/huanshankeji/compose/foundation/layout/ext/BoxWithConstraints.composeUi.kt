package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.ExperimentalFoundationApi
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@ExperimentalFoundationApi
@Composable
actual fun BoxWithConstraints(
    modifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) =
    androidx.compose.foundation.layout.BoxWithConstraints(
        modifier.platformModifier, contentAlignment.platformAlignment/*, false*/
    ) {
        BoxWithConstraintsScopeImpl(this, maxWidth, maxHeight).content()
    }

@Stable
class BoxWithConstraintsScopeImpl(
    override val platformBoxScope: BoxScope,
    override val maxWidth: Dp,
    override val maxHeight: Dp
) : BoxWithConstraintsScope
