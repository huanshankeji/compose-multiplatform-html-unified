package com.huanshankeji.compose.foundation.text.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.graphics.toAttrsWithColor
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
actual fun TaglessBasicText(text: String) =
    Text(text)

@InternalComposeApi
@Composable
fun CommonBasicText(text: String, modifier: Modifier, color: Color?) =
    Span(modifier.toAttrsWithColor(color)) { Text(text) }
