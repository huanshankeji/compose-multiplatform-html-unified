package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
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
internal fun CommonText(
    text: String,
    modifier: Modifier,
    color: Color?,
    fontSize: TextUnit,
    fontStyle: FontStyle?,
    fontWeight: FontWeight?,
    letterSpacing: TextUnit,
    textDecoration: TextDecoration?,
    textAlign: TextAlign?,
    lineHeight: TextUnit,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    minLines: Int,
) =
    Span(
        modifier.toAttrsWithTextStyles(
            color, fontSize, fontStyle, fontWeight, letterSpacing, textDecoration, textAlign,
            lineHeight, overflow, softWrap, maxLines, minLines,
        )
    ) {
        Text(text)
    }

@Composable
internal fun CommonText(
    text: AnnotatedString,
    modifier: Modifier,
    color: Color?,
    fontSize: TextUnit,
    fontStyle: FontStyle?,
    fontWeight: FontWeight?,
    letterSpacing: TextUnit,
    textDecoration: TextDecoration?,
    textAlign: TextAlign?,
    lineHeight: TextUnit,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    minLines: Int,
) =
    Span(
        modifier.toAttrsWithTextStyles(
            color, fontSize, fontStyle, fontWeight, letterSpacing, textDecoration, textAlign,
            lineHeight, overflow, softWrap, maxLines, minLines,
        )
    ) {
        AnnotatedStringText(text)
    }

private fun Modifier.toAttrsWithTextStyles(
    textColor: Color?,
    fontSize: TextUnit,
    fontStyle: FontStyle?,
    fontWeight: FontWeight?,
    letterSpacing: TextUnit,
    textDecoration: TextDecoration?,
    textAlign: TextAlign?,
    lineHeight: TextUnit,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    minLines: Int,
): AttrsScope<*>.() -> Unit =
    toAttrs({
        style {
            textColor?.let { color(it.platformValue) }
            applyFontSize(fontSize)
            fontStyle?.let { applyStyle(it) }
            fontWeight?.let { applyStyle(it) }
            applyLetterSpacing(letterSpacing)
            textDecoration?.let { applyStyle(it) }
            textAlign?.let { applyStyle(it) }
            applyLineHeight(lineHeight)
            applyStyle(overflow, softWrap, maxLines)
            if (minLines > 1) {
                // The CSS `lh` unit requires relatively modern browsers (Chrome 109+, Firefox 120+, Safari 16.4+).
                property("min-height", "${minLines}lh")
            }
        }
    })
