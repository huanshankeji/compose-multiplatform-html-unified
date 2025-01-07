package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.graphics.toPlatformValue

@Composable
actual fun Text(text: String, modifier: Modifier, color: Color?) =
    androidx.compose.material.Text(text, modifier.platformModifier, color.toPlatformValue())
