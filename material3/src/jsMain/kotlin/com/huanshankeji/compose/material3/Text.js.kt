package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.foundation.text.ext.CommonBasicText
import com.huanshankeji.compose.foundation.text.ext.CommonStyledText
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.style.TextAlign
import com.huanshankeji.compose.ui.text.style.TextDecoration
import com.huanshankeji.compose.ui.text.style.TextOverflow

@Deprecated("Use the overload with more parameters.", level = DeprecationLevel.HIDDEN)
@Composable
actual fun Text(text: String, modifier: Modifier, color: Color?) =
    @OptIn(InternalComposeApi::class)
    CommonBasicText(text, modifier, color)

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
    @OptIn(InternalComposeApi::class)
    CommonStyledText(
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
    @OptIn(InternalComposeApi::class)
    CommonStyledText(
        text, modifier, color,
        fontSize, fontStyle, fontWeight, letterSpacing, textDecoration, textAlign,
        lineHeight, overflow, softWrap, maxLines, minLines,
    )
