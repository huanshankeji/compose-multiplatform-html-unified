package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.foundation.text.ext.CommonBasicText
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.graphics.applyStyle as applyColorStyle
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.AnnotatedStringText
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.font.applyStyle
import com.huanshankeji.compose.ui.text.style.TextAlign
import com.huanshankeji.compose.ui.text.style.TextDecoration
import com.huanshankeji.compose.ui.text.style.TextOverflow
import com.huanshankeji.compose.ui.text.style.applyMinLines
import com.huanshankeji.compose.ui.text.style.applyStyle
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.ui.unit.applyFontSize
import com.huanshankeji.compose.ui.unit.applyLetterSpacing
import com.huanshankeji.compose.ui.unit.applyLineHeight
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

/*
 * Implementation note: Material 3 `Text` on JS DOM renders using `<span>` elements.
 * See `CommonText` for the shared implementation.
 *
 * For Material 3 `Text` specifically, using `<h1>`–`<h6>` or `<p>` elements could be
 * more semantically appropriate when a `TextStyle` parameter is supported (see #131):
 * - If `style` corresponds to a heading typography (e.g., `MaterialTheme.typography.headlineMedium`),
 *   the JS DOM implementation could render as an `<h2>` element for better HTML semantics.
 * - If `style` corresponds to body typography, a `<p>` element could be used.
 * - The mapping from Material Design typography roles to HTML elements would be:
 *   `displayLarge`–`displaySmall` / `headlineLarge`–`headlineSmall` → `<h1>`–`<h6>`
 *   `titleLarge`–`titleSmall` → `<h5>`–`<h6>` or styled `<span>`
 *   `bodyLarge`–`bodySmall` / `labelLarge`–`labelSmall` → `<p>` or `<span>`
 *
 * This approach would require `TextStyle` support (#131) and `MaterialTheme` support (#17)
 * to determine the typography role. Until then, `<span>` is used as the default.
 */

@Deprecated("Use the overload with more parameters.", level = DeprecationLevel.HIDDEN)
@Composable
actual fun Text(text: String, modifier: Modifier, color: Color?) =
    @OptIn(InternalComposeApi::class)
    CommonBasicText(text, modifier, color)

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

@Composable
private fun CommonText(
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
    content: @Composable () -> Unit,
) =
    Span(
        modifier.toAttrs {
            style {
                applyColorStyle(color)
                applyFontSize(fontSize)
                fontStyle?.let { applyStyle(it) }
                fontWeight?.let { applyStyle(it) }
                applyLetterSpacing(letterSpacing)
                textDecoration?.let { applyStyle(it) }
                textAlign?.let { applyStyle(it) }
                applyLineHeight(lineHeight)
                applyStyle(overflow, softWrap, maxLines)
                applyMinLines(minLines)
            }
        }
    ) {
        content()
    }

@Composable
actual fun Text(
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
    CommonText(
        modifier,
        color,
        fontSize, fontStyle, fontWeight, letterSpacing, textDecoration, textAlign,
        lineHeight, overflow, softWrap, maxLines, minLines,
    ) {
        Text(text)
    }

@Composable
actual fun Text(
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
    CommonText(
        modifier,
        color,
        fontSize, fontStyle, fontWeight, letterSpacing, textDecoration, textAlign,
        lineHeight, overflow, softWrap, maxLines, minLines,
    ) {
        AnnotatedStringText(text)
    }
