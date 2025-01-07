package com.huanshankeji.compose.foundation.text.input

import org.jetbrains.compose.web.attributes.InputMode

fun KeyboardType.toInputMode(): InputMode? =
    when (this) {
        KeyboardType.Text -> InputMode.Text
        KeyboardType.Ascii -> null
        KeyboardType.Number -> InputMode.Numeric
        KeyboardType.Phone -> InputMode.Tel
        KeyboardType.Uri -> InputMode.Url
        KeyboardType.Email -> InputMode.Email
        // TODO
        KeyboardType.Password -> null
        KeyboardType.NumberPassword -> null
        KeyboardType.Decimal -> InputMode.Decimal

        else -> throw IllegalArgumentException(toString())
    }
