package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable

@Immutable
expect value class TextOverflow internal constructor(internal val value: Int) {
    companion object {
        val Clip: TextOverflow
        val Ellipsis: TextOverflow
        val Visible: TextOverflow
    }
}
