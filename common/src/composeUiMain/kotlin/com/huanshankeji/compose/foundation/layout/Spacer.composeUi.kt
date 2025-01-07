package com.huanshankeji.compose.foundation.layout

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Spacer(modifier: Modifier) =
    androidx.compose.foundation.layout.Spacer(modifier.platformModifier)
