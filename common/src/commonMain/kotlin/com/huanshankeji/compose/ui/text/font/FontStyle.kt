package com.huanshankeji.compose.ui.text.font

expect value class FontStyle private constructor(val value: Int) {
    companion object {
        val Normal: FontStyle
        val Italic: FontStyle

        fun values(): List<FontStyle>
    }
}
