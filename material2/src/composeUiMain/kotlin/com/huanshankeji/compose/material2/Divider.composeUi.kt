package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Divider(modifier: Modifier) =
    androidx.compose.material.Divider(modifier.platformModifier)
