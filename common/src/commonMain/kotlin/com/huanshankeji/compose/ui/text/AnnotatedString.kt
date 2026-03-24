package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Immutable

// copied and adapted from `androidx.compose.ui.text.AnnotatedString`
@Immutable
class AnnotatedString internal constructor(
    val text: String,
    val spanStyles: List<Range<SpanStyle>>,
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

    @Immutable
    data class Range<T>(
        val item: T,
        val start: Int,
        val end: Int,
    )

    class Builder(capacity: Int = 16) {
        private val text = StringBuilder(capacity)
        private val spanStyles = mutableListOf<Range<SpanStyle>>()
        private val styleStack = mutableListOf<MutableRange<SpanStyle>>()

        val length: Int get() = text.length

        fun append(text: String) {
            this.text.append(text)
        }

        fun append(char: Char) {
            text.append(char)
        }

        fun append(text: AnnotatedString) {
            val start = this.text.length
            this.text.append(text.text)
            text.spanStyles.forEach {
                spanStyles.add(Range(it.item, it.start + start, it.end + start))
            }
        }

        fun pushStyle(style: SpanStyle): Int {
            val index = styleStack.size
            styleStack.add(MutableRange(style, text.length))
            return index
        }

        fun pop() {
            check(styleStack.isNotEmpty()) { "No styles to pop" }
            val entry = styleStack.removeLast()
            spanStyles.add(Range(entry.item, entry.start, text.length))
        }

        fun pop(index: Int) {
            check(index < styleStack.size) { "Invalid style index: $index" }
            while (styleStack.size > index) {
                pop()
            }
        }

        fun addStyle(style: SpanStyle, start: Int, end: Int) {
            spanStyles.add(Range(style, start, end))
        }

        fun toAnnotatedString(): AnnotatedString {
            // pop remaining open styles
            val remainingStyles = styleStack.map { Range(it.item, it.start, text.length) }
            return AnnotatedString(text.toString(), spanStyles + remainingStyles)
        }

        private class MutableRange<T>(val item: T, val start: Int)
    }
}

fun buildAnnotatedString(builder: AnnotatedString.Builder.() -> Unit): AnnotatedString =
    AnnotatedString.Builder().apply(builder).toAnnotatedString()

inline fun AnnotatedString.Builder.withStyle(
    style: SpanStyle,
    crossinline block: AnnotatedString.Builder.() -> Unit,
) {
    val index = pushStyle(style)
    block()
    pop(index)
}
