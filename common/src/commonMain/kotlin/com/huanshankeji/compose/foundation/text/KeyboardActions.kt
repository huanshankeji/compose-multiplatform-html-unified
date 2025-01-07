package com.huanshankeji.compose.foundation.text

import androidx.compose.runtime.Stable
import com.huanshankeji.compose.foundation.text.input.ImeAction
import kotlin.jvm.JvmInline

// copied and adapted from `androidx.compose.foundation.text`

@JvmInline
value class KeyboardActions(
    val onAny: (KeyboardActionScope.() -> Unit)? = null,
) {
    companion object {
        @Stable
        val Default: KeyboardActions = KeyboardActions()
    }
}

interface KeyboardActionScope {
    fun defaultKeyboardAction(imeAction: ImeAction)

    object DoNothingInstance : KeyboardActionScope {
        override fun defaultKeyboardAction(imeAction: ImeAction) {
            // do nothing for now
        }
    }
}
