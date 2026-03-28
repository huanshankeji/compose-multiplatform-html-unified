package com.huanshankeji.compose.ui.text.font

import androidx.compose.runtime.Immutable

@Immutable
expect value class FontStyle private constructor(val value: Int) {
    companion object {
        val Normal: FontStyle
        val Italic: FontStyle
    }
}
