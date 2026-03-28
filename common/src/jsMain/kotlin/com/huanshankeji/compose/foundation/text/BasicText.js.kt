package com.huanshankeji.compose.foundation.text

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.ColorProducer
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.AnnotatedStringText
import com.huanshankeji.compose.ui.text.style.TextOverflow
import com.huanshankeji.compose.ui.text.style.applyStyle
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

// TODO Check `com.varabyte.kobweb.silk.components.text.SpanText` again. Use it if it's better; otherwise, copy its GitHub permalink here and explain why.
/*
When using `com.varabyte.kobweb.silk.components.text.SpanText`:
> You can't access SilkTheme before first calling SilkApp
*/

@Composable
private fun CommonBasicText(
    modifier: Modifier,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    minLines: Int,
    color: ColorProducer?,
    content: @Composable () -> Unit,
) =
    Span(modifier.toAttrs {
        style {
            color?.let { color(it().platformValue) }
            applyStyle(overflow, softWrap, maxLines)
            if (minLines > 1)
                minHeight("${minLines}lh")
        }
    }) { content() }

@Composable
actual fun BasicText(
    text: String,
    modifier: Modifier,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    minLines: Int,
    color: ColorProducer?,
) =
    CommonBasicText(modifier, overflow, softWrap, maxLines, minLines, color) { Text(text) }

@Composable
actual fun BasicText(
    text: AnnotatedString,
    modifier: Modifier,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    minLines: Int,
    color: ColorProducer?,
) =
    CommonBasicText(modifier, overflow, softWrap, maxLines, minLines, color) { AnnotatedStringText(text) }
