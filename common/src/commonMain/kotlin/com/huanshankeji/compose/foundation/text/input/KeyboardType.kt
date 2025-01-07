package com.huanshankeji.compose.foundation.text.input

import androidx.compose.runtime.Stable

// copied and adapted from `androidx.compose.foundation.text.input.KeyboardType`
@kotlin.jvm.JvmInline
value class KeyboardType internal constructor(@Suppress("unused") private val value: Int) {

    override fun toString(): String {
        return when (this) {
            Text -> "Text"
            Ascii -> "Ascii"
            Number -> "Number"
            Phone -> "Phone"
            Uri -> "Uri"
            Email -> "Email"
            Password -> "Password"
            NumberPassword -> "NumberPassword"
            Decimal -> "Decimal"
            else -> "Invalid"
        }
    }

    companion object {
        @Stable
        val Text: KeyboardType = KeyboardType(1)

        // doesn't work on JS yet
        @Stable
        val Ascii: KeyboardType = KeyboardType(2)

        @Stable
        val Number: KeyboardType = KeyboardType(3)

        @Stable
        val Phone: KeyboardType = KeyboardType(4)

        @Stable
        val Uri: KeyboardType = KeyboardType(5)

        @Stable
        val Email: KeyboardType = KeyboardType(6)

        // doesn't work on JS yet
        @Stable
        val Password: KeyboardType = KeyboardType(7)

        // doesn't work on JS yet
        @Stable
        val NumberPassword: KeyboardType = KeyboardType(8)

        @Stable
        val Decimal: KeyboardType = KeyboardType(9)
    }
}
