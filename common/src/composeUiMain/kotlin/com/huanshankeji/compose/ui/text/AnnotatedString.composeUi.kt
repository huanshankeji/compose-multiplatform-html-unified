package com.huanshankeji.compose.ui.text

import androidx.compose.ui.text.AnnotatedString as PlatformAnnotatedString
import androidx.compose.ui.text.buildAnnotatedString as platformBuildAnnotatedString

actual typealias AnnotatedString = PlatformAnnotatedString

actual typealias AnnotatedStringBuilder = PlatformAnnotatedString.Builder

actual fun buildAnnotatedString(builder: AnnotatedStringBuilder.() -> Unit): AnnotatedString =
    platformBuildAnnotatedString(builder)

actual inline fun <R : Any> AnnotatedStringBuilder.withStyle(
    style: SpanStyle,
    block: AnnotatedStringBuilder.() -> R,
): R {
    val index = pushStyle(style.toPlatformValue())
    return try {
        block()
    } finally {
        pop(index)
    }
}
