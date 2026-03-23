package com.huanshankeji.compose.foundation.text.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.text.BasicText
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.ColorProducer

const val INLINE_TEXT_DEPRECATED_MESSAGE =
    "The word \"inline\" used here is inaccurate. Both text with or without a `span` tag can be inline in HTML terms."

@Deprecated(INLINE_TEXT_DEPRECATED_MESSAGE, ReplaceWith("TaglessBasicText(text)"))
@Composable
fun InlineBasicText(text: String) =
    TaglessBasicText(text)

/**
 * Delegates to raw text without any tag element on JS / Compose HTML.
 */
@Composable
expect fun TaglessBasicText(text: String)

/**
 * An alias for [BasicText].
 */
@Composable
fun SpanBasicText(text: String, modifier: Modifier = Modifier, color: ColorProducer? = null) =
    BasicText(text, modifier, color)
