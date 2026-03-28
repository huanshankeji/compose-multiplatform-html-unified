package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable

@Immutable
expect value class TextAlign internal constructor(internal val value: Int) {
    companion object {
        val Left: TextAlign
        val Right: TextAlign
        val Center: TextAlign
        val Justify: TextAlign
        val Start: TextAlign
        val End: TextAlign
    }
}
