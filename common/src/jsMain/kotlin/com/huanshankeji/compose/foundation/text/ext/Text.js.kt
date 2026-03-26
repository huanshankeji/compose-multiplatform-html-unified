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

/*
 * Implementation note: text is rendered using `<span>` elements on JS DOM.
 *
 * Alternative HTML elements were considered:
 * - `<h1>`–`<h6>`: Semantic heading elements. More conventional for heading text in HTML
 *   but Compose UI `Text` has no concept of heading level, and headings have built-in
 *   browser margins and block display that could break layouts.
 * - `<p>`: Paragraph element. More conventional for body text, and being block-level
 *   it would make `text-align` work naturally. However, `<p>` has default margins and
 *   block display behavior that differs from Compose UI's `Text` which is inline-positioned.
 *
 * `<span>` is used as the default because:
 * 1. It's inline, matching Compose UI `Text`'s positioning behavior (no extra margins or line breaks).
 * 2. It doesn't impose semantic meaning (heading, paragraph) that Compose UI `Text` doesn't have.
 * 3. CSS text styling properties are the same regardless of element type, so no additional
 *    Compose UI parameters are enabled by using alternative elements.
 *
 * Note: `text-align` on an inline `<span>` only takes effect when the element is made block-level
 * (e.g., via `fillMaxWidth` or when `maxLines` is set which adds `display: -webkit-box`).
 * This is consistent with Compose UI where `textAlign` only has visible effect when the `Text`
 * component is wider than its content.
 *
 * Future consideration: heading or paragraph composables (e.g., `HeadingText`, `ParagraphText`)
 * could be added as alternatives that render as `<h1>`–`<h6>` or `<p>` on JS DOM for
 * better HTML semantics and accessibility, while rendering as styled `Text` on Compose UI.
 */

@InternalComposeApi
@Composable
fun CommonBasicText(text: String, modifier: Modifier, color: Color?) =
    Span(modifier.toAttrsWithColor(color)) { Text(text) }

@InternalComposeApi
@Composable
fun CommonStyledText(
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

@InternalComposeApi
@Composable
fun CommonStyledText(
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
