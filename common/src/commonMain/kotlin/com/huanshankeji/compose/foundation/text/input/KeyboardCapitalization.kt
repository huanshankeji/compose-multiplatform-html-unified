package com.huanshankeji.compose.foundation.text.input

import androidx.compose.runtime.Stable

// copied and adapted from `androidx.compose.foundation.text.input.KeyboardCapitalization`
@kotlin.jvm.JvmInline
value class KeyboardCapitalization internal constructor(internal val value: Int) {
    override fun toString(): String {
        return when (this) {
            None -> "None"
            Characters -> "Characters"
            Words -> "Words"
            Sentences -> "Sentences"
            else -> "Invalid"
        }
    }

    companion object {
        @Stable
        val None = KeyboardCapitalization(0)

        @Stable
        val Characters = KeyboardCapitalization(1)

        @Stable
        val Words = KeyboardCapitalization(2)

        @Stable
        val Sentences = KeyboardCapitalization(3)
    }
}
