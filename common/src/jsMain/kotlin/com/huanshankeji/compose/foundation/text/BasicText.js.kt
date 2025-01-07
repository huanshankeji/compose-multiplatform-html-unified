package com.huanshankeji.compose.foundation.text

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.ColorProducer
import com.huanshankeji.compose.ui.graphics.toAttrsWithColor
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

/*
When using `com.varabyte.kobweb.silk.components.text.SpanText`:
> You can't access SilkTheme before first calling SilkApp
*/

@Composable
actual fun BasicText(text: String, modifier: Modifier, color: ColorProducer?) =
    Span(modifier.toAttrsWithColor(color)) { Text(text) }
