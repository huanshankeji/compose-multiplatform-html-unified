package com.huanshankeji.compose

import androidx.compose.runtime.Composable

@Composable
actual fun RawText(text: String) =
    androidx.compose.foundation.text.BasicText(text)
