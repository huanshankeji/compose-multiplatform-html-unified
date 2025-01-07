package com.huanshankeji.compose.foundation.text.input

import androidx.compose.ui.text.input.KeyboardType as PlatformKeyboardType

fun KeyboardType.toPlatformValue(): PlatformKeyboardType =
    when (this) {
        KeyboardType.Text -> PlatformKeyboardType.Text
        KeyboardType.Ascii -> PlatformKeyboardType.Ascii
        KeyboardType.Number -> PlatformKeyboardType.Number
        KeyboardType.Phone -> PlatformKeyboardType.Phone
        KeyboardType.Uri -> PlatformKeyboardType.Uri
        KeyboardType.Email -> PlatformKeyboardType.Email
        KeyboardType.Password -> PlatformKeyboardType.Password
        KeyboardType.NumberPassword -> PlatformKeyboardType.NumberPassword
        KeyboardType.Decimal -> PlatformKeyboardType.Decimal

        else -> throw IllegalArgumentException(toString())
    }

fun KeyboardType?.toPlatformValue(): PlatformKeyboardType =
    this?.toPlatformValue() ?: PlatformKeyboardType.Text
