package com.huanshankeji.compose

import androidx.compose.runtime.Composable

/** A raw text composable which doesn't add any element on web. */
@Composable
expect fun RawText(text: String)
