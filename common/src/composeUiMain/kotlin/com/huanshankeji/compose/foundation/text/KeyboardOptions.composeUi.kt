package com.huanshankeji.compose.foundation.text

import com.huanshankeji.compose.foundation.text.input.toPlatformValue
import androidx.compose.foundation.text.KeyboardOptions as PlatformKeyboardOptions

fun KeyboardOptions.toPlatformValue(): PlatformKeyboardOptions =
    // TODO deprecated
    PlatformKeyboardOptions(
        capitalization.toPlatformValue(),
        autoCorrect ?: true,
        keyboardType.toPlatformValue(),
        imeAction.toPlatformValue(),
        platformImeOptions?.platformValue
    )
