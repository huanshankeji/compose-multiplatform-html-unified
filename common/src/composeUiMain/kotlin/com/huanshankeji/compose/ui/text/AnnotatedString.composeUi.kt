package com.huanshankeji.compose.ui.text

import androidx.compose.ui.text.AnnotatedString as PlatformAnnotatedString

actual typealias AnnotatedString = PlatformAnnotatedString

actual typealias AnnotatedStringBuilder = PlatformAnnotatedString.Builder

actual fun buildAnnotatedString(builder: AnnotatedStringBuilder.() -> Unit): AnnotatedString =
    PlatformAnnotatedString.Builder().let { b ->
        builder(b)
        b.toAnnotatedString()
    }

actual inline fun <R : Any> AnnotatedStringBuilder.withStyle(
    style: SpanStyle,
    block: AnnotatedStringBuilder.() -> R,
): R {
    val index = (this as PlatformAnnotatedString.Builder).pushStyle(style.toPlatformValue())
    return try {
        block()
    } finally {
        (this as PlatformAnnotatedString.Builder).pop(index)
    }
}
