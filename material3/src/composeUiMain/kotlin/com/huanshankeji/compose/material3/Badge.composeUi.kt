package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Badge(
    modifier: Modifier,
    content: @Composable (() -> Unit)?
) =
    androidx.compose.material3.Badge(
        modifier.platformModifier,
        content = content?.let { { it() } }
    )
