package com.huanshankeji.compose.foundation.text

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

/*
When using `com.varabyte.kobweb.silk.components.text.SpanText`:
> You can't access SilkTheme before first calling SilkApp
*/

@Composable
actual fun BasicText(text: String) =
    Span { Text(text) }

@Composable
actual fun BasicText(text: String, modifier: Modifier) =
    Span(modifier.toAttrs()) { Text(text) }
