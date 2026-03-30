package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.buildAnnotatedString as platformBuildAnnotatedString
import androidx.compose.ui.text.AnnotatedString as PlatformAnnotatedString

@Immutable
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

    @Immutable
    actual class Range<T> actual constructor(
        actual val item: T,
        actual val start: Int,
        actual val end: Int,
    )

    actual class Builder(val platformBuilder: PlatformAnnotatedString.Builder) {
        actual constructor(capacity: Int) : this(PlatformAnnotatedString.Builder(capacity))

        /** Create a [Builder] instance using the given [String]. */
        actual constructor(text: String) : this(PlatformAnnotatedString.Builder(text))

        /** Create a [Builder] instance using the given [AnnotatedString]. */
        actual constructor(text: AnnotatedString) : this(PlatformAnnotatedString.Builder(text.platformValue))

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

        actual fun toAnnotatedString(): AnnotatedString =
            AnnotatedString(platformBuilder.toAnnotatedString())
    }
}

actual fun buildAnnotatedString(builder: AnnotatedString.Builder.() -> Unit): AnnotatedString =
    AnnotatedString.Builder().apply(builder).toAnnotatedString()

actual inline fun <R : Any> AnnotatedString.Builder.withStyle(
    style: SpanStyle,
    block: AnnotatedString.Builder.() -> R,
): R {
    val index = platformBuilder.pushStyle(style.toPlatformValue())
    return try {
        block(this)
    } finally {
        platformBuilder.pop(index)
    }
}
