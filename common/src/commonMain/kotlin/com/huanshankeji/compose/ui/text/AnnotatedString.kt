package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Immutable

/**
 * Initially supported subset of `androidx.compose.ui.text.AnnotatedString`.
 *
 * The original corresponding Compose UI class implements [CharSequence] but it would be inefficient to implement here with the wrapping,
 * and also it's not really needed now, so the class doesn't implement [CharSequence] for now.
 * Possibly a `toCharSequence()` extension function could be added if needed in the future.
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
) /* : CharSequence */ {
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
        /** Create a [Builder] instance using the given [String]. */
        constructor(text: String)

        /** Create a [Builder] instance using the given [AnnotatedString]. */
        constructor(text: AnnotatedString)

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

/**
 * Pushes [style] to the [AnnotatedString.Builder], executes [block] and then pops the [style].
 *
 * @param style [SpanStyle] to be applied
 * @param block function to be executed
 * @return result of the [block]
 * @see AnnotatedString.Builder.pushStyle
 * @see AnnotatedString.Builder.pop
 */
inline fun <R : Any> AnnotatedString.Builder.withStyle(
    style: SpanStyle,
    block: AnnotatedString.Builder.() -> R,
): R {
    val index = pushStyle(style)
    return try {
        block(this)
    } finally {
        pop(index)
    }
}
