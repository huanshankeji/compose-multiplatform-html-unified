package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Immutable

/**
 * Initially supported subset of `androidx.compose.ui.text.AnnotatedString`.
 *
 * The following Compose UI features are not yet supported:
 * - `paragraphStyles: List<Range<ParagraphStyle>>` — requires porting `ParagraphStyle` (see #131);
 *   CSS paragraph styling differs significantly from Compose UI's paragraph model.
 *   On JS DOM, using `<p>` HTML elements to wrap paragraph ranges could enable
 *   paragraph-level styling (e.g., `textAlign`, `textIndent`, `lineHeight`) more naturally
 *   than on `<span>`, but this would require splitting the text into paragraph elements
 *   at paragraph boundaries. This is feasible but adds complexity to the JS DOM rendering.
 * - `getStringAnnotations` / `getTtsAnnotations` / `getUrlAnnotations` — annotation-based APIs require more complex infrastructure
 * - `AnnotatedString.Builder.pushStringAnnotation` / `pushTtsAnnotation` / `pushUrlAnnotation` — annotation push APIs
 */
@Immutable
expect class AnnotatedString(
    text: String,
    spanStyles: List<Range<SpanStyle>> = emptyList(),
    // paragraphStyles: List<Range<ParagraphStyle>> = emptyList(),
) {
    val text: String
    val spanStyles: List<Range<SpanStyle>>

    operator fun plus(other: AnnotatedString): AnnotatedString
    // subSequence(range: TextRange) — requires porting TextRange

    @Immutable
    class Range<T> {
        val item: T
        val start: Int
        val end: Int

        constructor(item: T, start: Int, end: Int)
    }

    class Builder(capacity: Int = 16) {
        val length: Int

        fun append(text: String)
        fun append(char: Char)
        fun append(text: AnnotatedString)

        fun pushStyle(style: SpanStyle): Int
        fun pop()
        fun pop(index: Int)
        fun addStyle(style: SpanStyle, start: Int, end: Int)

        fun toAnnotatedString(): AnnotatedString
    }
}

expect fun buildAnnotatedString(builder: AnnotatedString.Builder.() -> Unit): AnnotatedString

inline fun AnnotatedString.Builder.withStyle(
    style: SpanStyle,
    crossinline block: AnnotatedString.Builder.() -> Unit,
) {
    val index = pushStyle(style)
    block()
    pop(index)
}
