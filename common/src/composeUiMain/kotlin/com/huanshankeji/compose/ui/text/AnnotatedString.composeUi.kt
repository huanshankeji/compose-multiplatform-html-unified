package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.buildAnnotatedString as platformBuildAnnotatedString
import androidx.compose.ui.text.AnnotatedString as PlatformAnnotatedString

actual class AnnotatedString(val platformValue: PlatformAnnotatedString) {
    actual constructor(
        text: String,
        spanStyles: List<Range<SpanStyle>>,
    ) : this(
        platformBuildAnnotatedString {
            append(text)
            for (range in spanStyles) {
                addStyle(range.item.toPlatformValue(), range.start, range.end)
            }
        }
    )

    actual val text: String get() = platformValue.text
    actual val spanStyles: List<Range<SpanStyle>>
        get() = platformValue.spanStyles.map {
            Range(SpanStyle(it.item), it.start, it.end)
        }

    @Stable
    actual operator fun plus(other: AnnotatedString): AnnotatedString =
        AnnotatedString(platformValue + other.platformValue)

    override fun toString(): String = platformValue.toString()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AnnotatedString) return false
        return platformValue == other.platformValue
    }

    override fun hashCode(): Int = platformValue.hashCode()

    fun toPlatformValue(): PlatformAnnotatedString = platformValue

    actual class Range<T> actual constructor(
        actual val item: T,
        actual val start: Int,
        actual val end: Int,
    )

    actual class Builder(val platformBuilder: PlatformAnnotatedString.Builder) {
        actual constructor(capacity: Int) : this(PlatformAnnotatedString.Builder(capacity))

        actual val length: Int get() = platformBuilder.length

        actual fun append(text: String) {
            platformBuilder.append(text)
        }

        actual fun append(char: Char) {
            platformBuilder.append(char)
        }

        actual fun append(text: AnnotatedString) {
            platformBuilder.append(text.platformValue)
        }

        actual fun pushStyle(style: SpanStyle): Int =
            platformBuilder.pushStyle(style.toPlatformValue())

        actual fun pop() {
            platformBuilder.pop()
        }

        actual fun pop(index: Int) {
            platformBuilder.pop(index)
        }

        actual fun addStyle(style: SpanStyle, start: Int, end: Int) {
            platformBuilder.addStyle(style.toPlatformValue(), start, end)
        }

        actual fun toAnnotatedString(): AnnotatedString =
            AnnotatedString(platformBuilder.toAnnotatedString())
    }
}

actual fun buildAnnotatedString(builder: AnnotatedString.Builder.() -> Unit): AnnotatedString =
    AnnotatedString.Builder().apply(builder).toAnnotatedString()
