package com.huanshankeji.compose.foundation.text.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.graphics.toAttrsWithColor
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.AnnotatedStringText
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.font.applyStyle
import com.huanshankeji.compose.ui.text.style.TextAlign
import com.huanshankeji.compose.ui.text.style.TextDecoration
import com.huanshankeji.compose.ui.text.style.TextOverflow
import com.huanshankeji.compose.ui.text.style.applyStyle
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.ui.unit.applyFontSize
import com.huanshankeji.compose.ui.unit.applyLetterSpacing
import com.huanshankeji.compose.ui.unit.applyLineHeight
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
actual fun TaglessBasicText(text: String) =
    Text(text)

@Deprecated(
    "Only used by deprecated functions now.",
    level = DeprecationLevel.WARNING
)
@InternalComposeApi
@Composable
fun CommonBasicText(text: String, modifier: Modifier, color: Color?) =
    Span(modifier.toAttrsWithColor(color)) { Text(text) }
