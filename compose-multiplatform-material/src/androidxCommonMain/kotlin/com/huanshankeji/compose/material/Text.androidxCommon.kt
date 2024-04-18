package com.huanshankeji.compose.material

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Text(text: String, modifier: Modifier) =
    androidx.compose.material.Text(text, modifier.platformModifier)
