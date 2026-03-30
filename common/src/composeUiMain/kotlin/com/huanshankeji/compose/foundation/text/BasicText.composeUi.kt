package com.huanshankeji.compose.foundation.text

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.ColorProducer
import com.huanshankeji.compose.ui.graphics.toNullablePlatformValue
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.text.BasicText as PlatformBasicText

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
    PlatformBasicText(
        text,
        modifier.platformModifier,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color.toNullablePlatformValue(),
    )

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
    PlatformBasicText(
        text.platformValue,
        modifier.platformModifier,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color.toNullablePlatformValue(),
    )
