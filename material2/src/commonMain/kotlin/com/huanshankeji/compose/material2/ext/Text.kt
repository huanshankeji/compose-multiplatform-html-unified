package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.text.ext.INLINE_TEXT_DEPRECATED_MESSAGE
import com.huanshankeji.compose.material2.Text
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

/**
 * An explicit alias of [Text].
 */
@Composable
inline fun MaterialText(text: String, modifier: Modifier = Modifier, color: Color? = null) =
    Text(text, modifier, color)

@Deprecated(INLINE_TEXT_DEPRECATED_MESSAGE, ReplaceWith("TaglessText(text)"))
@Composable
fun InlineText(text: String) =
    TaglessText(text)

/**
 * Delegates to raw text without any tag element on JS / Compose HTML.
 * @see com.huanshankeji.compose.foundation.text.ext.TaglessBasicText
 */
@Composable
expect fun TaglessText(text: String)

/**
 * An alias for [Text].
 */
@Composable
fun SpanText(text: String, modifier: Modifier = Modifier, color: Color? = null) =
    Text(text, modifier, color)
