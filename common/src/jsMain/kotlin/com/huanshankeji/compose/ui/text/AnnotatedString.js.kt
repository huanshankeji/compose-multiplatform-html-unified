package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

actual class AnnotatedString actual constructor(
    actual val text: String,
    actual val spanStyles: List<Range<SpanStyle>>,
) {
    override fun toString(): String =
        text

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AnnotatedString) return false
        if (text != other.text) return false
        if (spanStyles != other.spanStyles) return false
        return true
    }

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + spanStyles.hashCode()
        return result
    }

    @Stable
    actual operator fun plus(other: AnnotatedString): AnnotatedString =
        with(Builder()) {
            append(this@AnnotatedString)
            append(other)
            toAnnotatedString()
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

@Composable
fun AnnotatedStringText(annotatedString: AnnotatedString) {
    if (annotatedString.spanStyles.isEmpty()) {
        Text(annotatedString.text)
    } else {
        val text = annotatedString.text
        val breakpoints = mutableSetOf(0, text.length)
        for (range in annotatedString.spanStyles) {
            breakpoints.add(range.start)
            breakpoints.add(range.end)
        }
        val breakpointList = breakpoints.sorted()

        // Precompute active styles per segment using a sweep-line approach
        // to avoid O(N_segments * N_ranges) filtering.
        val ranges = annotatedString.spanStyles
        val rangesByStart = ranges.groupBy { it.start }
        val rangesByEnd = ranges.groupBy { it.end }
        val activeStyles = mutableListOf<AnnotatedString.Range<SpanStyle>>()

        for (i in 0 until breakpointList.size - 1) {
            val start = breakpointList[i]
            val end = breakpointList[i + 1]
            if (start >= end) continue

            // Update active styles: remove ranges ending at this breakpoint, add ranges starting here.
            rangesByEnd[start]?.let { toRemove -> activeStyles.removeAll(toRemove) }
            rangesByStart[start]?.let { toAdd -> activeStyles.addAll(toAdd) }

            val segment = text.substring(start, end)

            if (activeStyles.isEmpty()) {
                Text(segment)
            } else {
                Span({
                    style {
                        // Apply styles sorted by start ascending, end descending (outer first, inner last)
                        // so inner/narrower styles override outer ones for the same CSS property.
                        for (range in activeStyles.sortedWith(compareBy<AnnotatedString.Range<SpanStyle>> { it.start }.thenByDescending { it.end })) {
                            applyStyle(range.item)
                        }
                    }
                }) {
                    Text(segment)
                }
            }
        }
    }
}
