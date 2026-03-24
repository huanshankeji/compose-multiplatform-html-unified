package com.huanshankeji.compose.ui.text

import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.AnnotatedString as PlatformAnnotatedString

fun AnnotatedString.toPlatformValue(): PlatformAnnotatedString =
    buildAnnotatedString {
        append(text)
        for (range in spanStyles) {
            addStyle(range.item.toPlatformValue(), range.start, range.end)
        }
    }
