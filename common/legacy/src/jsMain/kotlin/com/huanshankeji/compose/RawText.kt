package com.huanshankeji.compose

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Text

@Composable
actual fun RawText(text: String) =
    Text(text)