package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.text.ext.INLINE_TEXT_DEPRECATED_MESSAGE
import com.huanshankeji.compose.material3.Text
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
 * Delegates to raw text without any element on JS / Compose HTML.
 * @see com.huanshankeji.compose.foundation.text.ext.TaglessBasicText
 */
@Composable
expect fun TaglessText(text: String)

@Composable
fun SpanText(text: String, modifier: Modifier = Modifier, color: Color? = null) =
    Text(text, modifier, color)


fun String.toTextWithModifier(): @Composable (Modifier) -> Unit =
    { modifier -> Text(this, modifier) }

fun String?.toNullableTextWithModifier(): @Composable ((Modifier) -> Unit)? =
    this?.toTextWithModifier()

fun String.toTaglessText(): @Composable () -> Unit =
    { TaglessText(this) }

fun String?.toNullableTaglessText(): @Composable (() -> Unit)? =
    this?.toTaglessText()
