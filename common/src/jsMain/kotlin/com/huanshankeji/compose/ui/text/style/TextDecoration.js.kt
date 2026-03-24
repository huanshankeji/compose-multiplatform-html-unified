package com.huanshankeji.compose.ui.text.style

import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.textDecorationLine

fun TextDecoration.toCssValue(): String =
    when {
        this == TextDecoration.None -> "none"
        this == TextDecoration.Underline -> "underline"
        this == TextDecoration.LineThrough -> "line-through"
        else -> buildList {
            if (TextDecoration.Underline in this@toCssValue) add("underline")
            if (TextDecoration.LineThrough in this@toCssValue) add("line-through")
        }.joinToString(" ")
    }

fun StyleScope.applyStyle(textDecoration: TextDecoration) {
    textDecorationLine(textDecoration.toCssValue())
}
