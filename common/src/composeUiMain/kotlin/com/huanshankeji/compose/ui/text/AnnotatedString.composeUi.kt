package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.buildAnnotatedString as platformBuildAnnotatedString
import androidx.compose.ui.text.AnnotatedString as PlatformAnnotatedString
import androidx.compose.ui.text.withStyle as platformWithStyle

// TODO try typealias
//actual typealias AnnotatedString = PlatformAnnotatedString

// TODO try value class

@Immutable
actual class AnnotatedString(val platformValue: PlatformAnnotatedString) {
    actual val text: String get() = platformValue.text

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

    actual class Builder(val platformValue: PlatformAnnotatedString.Builder) {
        /*actual*/ constructor(capacity: Int) : this(PlatformAnnotatedString.Builder(capacity))

        actual constructor(text: String) : this(PlatformAnnotatedString.Builder(text))

        actual constructor(text: AnnotatedString) : this(PlatformAnnotatedString.Builder(text.platformValue))

        /*actual*/ val length: Int get() = platformValue.length

        actual fun append(text: String) =
            platformValue.append(text)

        actual fun append(char: Char) =
            Builder(platformValue.append(char))

        actual fun append(text: AnnotatedString) =
            platformValue.append(text.platformValue)

        actual fun toAnnotatedString(): AnnotatedString =
            AnnotatedString(platformValue.toAnnotatedString())
    }
}

actual fun buildAnnotatedString(builder: AnnotatedString.Builder.() -> Unit): AnnotatedString =
    AnnotatedString(platformBuildAnnotatedString { AnnotatedString.Builder(this).builder() })

actual inline fun <R : Any> AnnotatedString.Builder.withStyle(
    style: SpanStyle,
    block: AnnotatedString.Builder.() -> R,
): R =
    platformValue.platformWithStyle(style.toPlatformValue()) {
        AnnotatedString.Builder(this).block()
    }
