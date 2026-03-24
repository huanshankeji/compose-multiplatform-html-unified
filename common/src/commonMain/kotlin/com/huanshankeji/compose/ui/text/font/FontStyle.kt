package com.huanshankeji.compose.ui.text.font

import androidx.compose.runtime.Immutable

@Immutable
expect value class FontStyle internal constructor(internal val value: Int) {
    companion object {
        val Normal: FontStyle
        val Italic: FontStyle
    }
}
