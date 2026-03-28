package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.graphics.toAttrsWithColor
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.style.TextAlign
import com.huanshankeji.compose.ui.text.style.TextDecoration
import com.huanshankeji.compose.ui.text.style.TextOverflow
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

/*
 * Implementation note: Material 3 `Text` on JS DOM renders using `<span>` elements.
 * See `CommonText.js.kt` in this package for the shared implementation.
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
    Span(modifier.toAttrsWithColor(color)) { Text(text) }

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
        text, modifier, color,
        fontSize, fontStyle, fontWeight, letterSpacing, textDecoration, textAlign,
        lineHeight, overflow, softWrap, maxLines, minLines,
    )

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
        text, modifier, color,
        fontSize, fontStyle, fontWeight, letterSpacing, textDecoration, textAlign,
        lineHeight, overflow, softWrap, maxLines, minLines,
    )
