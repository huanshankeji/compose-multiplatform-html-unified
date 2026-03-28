package com.huanshankeji.compose.ui.text.style

expect value class TextAlign internal constructor(val value: Int) {
    companion object {
        val Left: TextAlign
        val Right: TextAlign
        val Center: TextAlign
        val Justify: TextAlign
        val Start: TextAlign
        val End: TextAlign
        // Unspecified is not yet supported since it's not used in our current APIs.

        fun values(): List<TextAlign>
    }
}

inline val TextAlign.isSpecified: Boolean
    get() = value != 0

inline fun TextAlign.takeOrElse(block: () -> TextAlign): TextAlign =
    if (isSpecified) this else block()
