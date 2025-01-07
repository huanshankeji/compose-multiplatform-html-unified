package com.huanshankeji.compose.foundation.text.input

fun KeyboardCapitalization.toAutoCapitalizeAttrValue() =
    @Suppress("DEPRECATION")
    toString().decapitalize()
