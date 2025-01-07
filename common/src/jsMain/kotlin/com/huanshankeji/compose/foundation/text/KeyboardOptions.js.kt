package com.huanshankeji.compose.foundation.text

import com.huanshankeji.compose.foundation.text.input.toAutoCapitalizeAttrValue
import com.huanshankeji.compose.foundation.text.input.toInputMode
import com.huanshankeji.compose.web.attributes.autocorrect
import com.huanshankeji.compose.web.attributes.ext.autoCapitalizeRequiringValid
import com.huanshankeji.compose.web.attributes.ext.enterKeyHintIfValid
import org.jetbrains.compose.web.attributes.AttrsScope

fun AttrsScope<*>.attrsFrom(keyboardOptions: KeyboardOptions) {
    with(keyboardOptions) {
        capitalization?.let { autoCapitalizeRequiringValid(it.toAutoCapitalizeAttrValue()) }
        autoCorrect?.let {
            // https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/spellcheck
            spellCheck(it)
            autocorrect(it)
        }
        // https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/inputmode
        keyboardType?.toInputMode()?.let { inputMode(it) }
        imeAction?.let {
            // Also see: https://stackoverflow.com/questions/24278088/showing-search-button-as-submit-button-in-mobile-devices-keyboards
            @Suppress("DEPRECATION")
            enterKeyHintIfValid(it.toString().decapitalize())
        }
        //platformImeOptions
    }
}
