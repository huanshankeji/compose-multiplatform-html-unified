package com.huanshankeji.compose.foundation.text

import com.huanshankeji.compose.foundation.text.input.ImeAction
import com.huanshankeji.compose.foundation.text.input.toPlatformValue
import androidx.compose.foundation.text.KeyboardActionScope as PlatformKeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions as PlatformKeyboardActions

fun KeyboardActions.toPlatformValue(): PlatformKeyboardActions =
    onAny?.let {
        PlatformKeyboardActions {
            toCommonValue().it()
        }
    } ?: PlatformKeyboardActions()

fun PlatformKeyboardActionScope.toCommonValue(): KeyboardActionScope =
    object : KeyboardActionScope {
        override fun defaultKeyboardAction(imeAction: ImeAction) =
            this@toCommonValue.defaultKeyboardAction(imeAction.toPlatformValue())
    }
