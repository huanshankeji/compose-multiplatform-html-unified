package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.graphics.toPlatformValue
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.font.toPlatformValue
import com.huanshankeji.compose.ui.text.style.TextAlign
import com.huanshankeji.compose.ui.text.style.TextDecoration
import com.huanshankeji.compose.ui.text.style.TextOverflow
import com.huanshankeji.compose.ui.text.style.toPlatformValue
import com.huanshankeji.compose.ui.text.toPlatformValue

/**
 * @see com.huanshankeji.compose.material3.ext.TaglessText
 */
@Composable
actual fun Text(text: String, modifier: Modifier, color: Color?) =
    androidx.compose.material3.Text(text, modifier.platformModifier, color.toPlatformValue())

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
    androidx.compose.material3.Text(
        text = text,
        modifier = modifier.platformModifier,
        color = color.toPlatformValue(),
        fontSize = fontSize,
        fontStyle = fontStyle?.toPlatformValue(),
        fontWeight = fontWeight?.toPlatformValue(),
        letterSpacing = letterSpacing,
        textDecoration = textDecoration?.toPlatformValue(),
        textAlign = textAlign?.toPlatformValue(),
        lineHeight = lineHeight,
        overflow = overflow.toPlatformValue(),
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
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
    androidx.compose.material3.Text(
        text = text.toPlatformValue(),
        modifier = modifier.platformModifier,
        color = color.toPlatformValue(),
        fontSize = fontSize,
        fontStyle = fontStyle?.toPlatformValue(),
        fontWeight = fontWeight?.toPlatformValue(),
        letterSpacing = letterSpacing,
        textDecoration = textDecoration?.toPlatformValue(),
        textAlign = textAlign?.toPlatformValue(),
        lineHeight = lineHeight,
        overflow = overflow.toPlatformValue(),
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
    )
