package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Immutable

/**
 * Initially supported subset of `androidx.compose.ui.text.AnnotatedString`.
 *
 * The original corresponding Compose UI class implements [CharSequence] but it would be inefficient to implement here with the wrapping,
 * and also it's not really needed now, so the class doesn't implement [CharSequence] for now.
 * Possibly a `toCharSequence()` extension function could be added if needed in the future.
 *
 * On JS DOM, the `AnnotatedString` uses a nestable tree/node design where `withStyle` calls
 * map naturally to nested HTML `<span>` elements, rather than replicating the Compose UI
 * range-based (`spanStyles: List<Range>`) design. This avoids the unnecessary complexity of
 * converting ranges to segments and back to `<span>` tags. As a consequence, `pushStyle`,
 * `addStyle`, and `pop` are not provided; use `withStyle` instead.
 * See commit d7d71c38ff888738e06871d1d8662c8556c508b5 for the previous revision that replicated the Compose UI design.
 *
 * The following Compose UI features are not yet supported:
 * - `paragraphStyles: List<Range<ParagraphStyle>>` — requires porting `ParagraphStyle` (see #131);
 *   CSS paragraph styling differs significantly from Compose UI's paragraph model.
 *   On JS DOM, using `<p>` HTML elements to wrap paragraph ranges could enable
 *   paragraph-level styling (e.g., `textAlign`, `textIndent`, `lineHeight`) more naturally
 *   than on `<span>`, but this would require splitting the text into paragraph elements
 *   at paragraph boundaries. This is feasible but adds complexity to the JS DOM rendering.
 * - `getStringAnnotations` / `getTtsAnnotations` / `getUrlAnnotations` — annotation-based APIs require more complex infrastructure
 * - `AnnotatedStringBuilder.pushStringAnnotation` / `pushTtsAnnotation` / `pushUrlAnnotation` — annotation push APIs
 * - `AnnotatedStringBuilder.pushStyle` / `addStyle` / `pop` — use `withStyle` instead;
 *   on JS DOM the tree/node design makes these unnecessary (see above)
 */
@Immutable
expect class AnnotatedString {
    val text: String

    operator fun plus(other: AnnotatedString): AnnotatedString
    // subSequence(range: TextRange) — requires porting TextRange
}

/**
 * Builder for creating [AnnotatedString] instances with styled text.
 *
 * This is a top-level class (rather than nested inside [AnnotatedString]) to allow
 * `actual typealias AnnotatedString = PlatformAnnotatedString` on Compose UI platforms,
 * working around a Kotlin limitation where nested classes of expect classes cannot be
 * resolved through typealiases from consuming modules.
 *
 * Corresponds to `androidx.compose.ui.text.AnnotatedString.Builder`.
 */
expect class AnnotatedStringBuilder {
    /** Create an [AnnotatedStringBuilder] instance using the given [String]. */
    constructor(text: String)

    /** Create an [AnnotatedStringBuilder] instance using the given [AnnotatedString]. */
    constructor(text: AnnotatedString)

    // Implement if needed.
    //val length: Int

    fun append(text: String)

    // This returns an `AnnotatedStringBuilder` to be consistent with the Compose UI API which extends it from `Appendable`.
    fun append(char: Char): AnnotatedStringBuilder
    fun append(text: AnnotatedString)

    fun toAnnotatedString(): AnnotatedString
}

expect fun buildAnnotatedString(builder: AnnotatedStringBuilder.() -> Unit): AnnotatedString

expect inline fun <R : Any> AnnotatedStringBuilder.withStyle(
    style: SpanStyle,
    block: AnnotatedStringBuilder.() -> R,
): R
