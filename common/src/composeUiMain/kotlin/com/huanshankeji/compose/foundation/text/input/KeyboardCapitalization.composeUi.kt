package com.huanshankeji.compose.foundation.text.input

import androidx.compose.ui.text.input.KeyboardCapitalization as PlatformKeyboardCapitalization

fun KeyboardCapitalization.toPlatformValue(): PlatformKeyboardCapitalization =
    when (this) {
        KeyboardCapitalization.None -> PlatformKeyboardCapitalization.None
        KeyboardCapitalization.Characters -> PlatformKeyboardCapitalization.Characters
        KeyboardCapitalization.Words -> PlatformKeyboardCapitalization.Words
        KeyboardCapitalization.Sentences -> PlatformKeyboardCapitalization.Sentences

        else -> throw IllegalArgumentException(toString())
    }

fun KeyboardCapitalization?.toPlatformValue(): PlatformKeyboardCapitalization =
    this?.toPlatformValue() ?: PlatformKeyboardCapitalization.None
