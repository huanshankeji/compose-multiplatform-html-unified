package com.huanshankeji.compose.foundation.text.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.text.BasicText

@Suppress("DEPRECATION")
@Composable
actual fun TaglessBasicText(text: String) =
    BasicText(text)
