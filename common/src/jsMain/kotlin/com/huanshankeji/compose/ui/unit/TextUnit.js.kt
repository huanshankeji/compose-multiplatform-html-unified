package com.huanshankeji.compose.ui.unit

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import org.jetbrains.compose.web.css.CSSNumericValue
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px

fun TextUnit.toCssValue(): CSSNumericValue<*> =
    when (type) {
        TextUnitType.Sp -> value.px // Sp maps to CSS px in JS DOM (like Dp, though Sp is semantically for text sizes)
        TextUnitType.Em -> value.em
        else -> throw IllegalArgumentException("Unspecified TextUnit cannot be converted to CSS")
    }

fun StyleScope.applyFontSize(textUnit: TextUnit) {
    if (textUnit != TextUnit.Unspecified)
        property("font-size", textUnit.toCssValue())
}

fun StyleScope.applyLetterSpacing(textUnit: TextUnit) {
    if (textUnit != TextUnit.Unspecified)
        property("letter-spacing", textUnit.toCssValue())
}

fun StyleScope.applyLineHeight(textUnit: TextUnit) {
    if (textUnit != TextUnit.Unspecified)
        property("line-height", textUnit.toCssValue())
}
