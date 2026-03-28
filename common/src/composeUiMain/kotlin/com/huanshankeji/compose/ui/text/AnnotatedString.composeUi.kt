package com.huanshankeji.compose.ui.text

import androidx.compose.ui.text.buildAnnotatedString as platformBuildAnnotatedString
import androidx.compose.ui.text.AnnotatedString as PlatformAnnotatedString

actual class AnnotatedString actual constructor(
    actual val text: String,
    actual val spanStyles: List<Range<SpanStyle>>,
) {
    fun toPlatformValue(): PlatformAnnotatedString =
        platformBuildAnnotatedString {
            append(text)
            for (range in spanStyles) {
                addStyle(range.item.toPlatformValue(), range.start, range.end)
            }
        }

    actual class Range<T> actual constructor(
        actual val item: T,
        actual val start: Int,
        actual val end: Int,
    )

    actual class Builder actual constructor(capacity: Int) {
        private val text = StringBuilder(capacity)
        private val spanStyles = mutableListOf<Range<SpanStyle>>()
        private val styleStack = mutableListOf<MutableRange<SpanStyle>>()

        actual val length: Int get() = text.length

        actual fun append(text: String) {
            this.text.append(text)
        }

        actual fun append(char: Char) {
            text.append(char)
        }

        actual fun append(text: AnnotatedString) {
            val start = this.text.length
            this.text.append(text.text)
            text.spanStyles.forEach {
                spanStyles.add(Range(it.item, it.start + start, it.end + start))
            }
        }

        actual fun pushStyle(style: SpanStyle): Int {
            val index = styleStack.size
            styleStack.add(MutableRange(style, text.length))
            return index
        }

        actual fun pop() {
            check(styleStack.isNotEmpty()) { "No styles to pop" }
            val entry = styleStack.removeLast()
            spanStyles.add(Range(entry.item, entry.start, text.length))
        }

        actual fun pop(index: Int) {
            check(index < styleStack.size) { "Invalid style index: $index" }
            while (styleStack.size > index) {
                pop()
            }
        }

        actual fun addStyle(style: SpanStyle, start: Int, end: Int) {
            spanStyles.add(Range(style, start, end))
        }

        actual fun toAnnotatedString(): AnnotatedString {
            val remainingStyles = styleStack.map { Range(it.item, it.start, text.length) }
            return AnnotatedString(text.toString(), spanStyles + remainingStyles)
        }

        private class MutableRange<T>(val item: T, val start: Int)
    }
}

actual fun buildAnnotatedString(builder: AnnotatedString.Builder.() -> Unit): AnnotatedString =
    AnnotatedString.Builder().apply(builder).toAnnotatedString()
