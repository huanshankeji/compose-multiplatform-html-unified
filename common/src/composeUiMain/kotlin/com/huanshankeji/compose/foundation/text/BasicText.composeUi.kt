package com.huanshankeji.compose.foundation.text

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.ColorProducer
import com.huanshankeji.compose.ui.graphics.toNullablePlatformValue
import androidx.compose.foundation.text.BasicText as PlatformBasicText

@Composable
actual fun BasicText(text: String, modifier: Modifier, color: ColorProducer?) =
    PlatformBasicText(text, modifier.platformModifier, color = color.toNullablePlatformValue())
