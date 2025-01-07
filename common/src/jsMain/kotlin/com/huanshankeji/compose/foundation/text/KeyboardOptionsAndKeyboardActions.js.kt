package com.huanshankeji.compose.foundation.text

import org.jetbrains.compose.web.attributes.AttrsScope
import org.w3c.dom.HTMLElement

fun AttrsScope<HTMLElement>.attrsFrom(keyboardOptions: KeyboardOptions, keyboardActions: KeyboardActions) {
    attrsFrom(keyboardOptions)
    keyboardActions.onAny?.let {
        onKeyDown {
            if (it.key == "Enter")
                KeyboardActionScope.DoNothingInstance.it()
        }
    }
}
