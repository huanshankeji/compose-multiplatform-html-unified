package com.huanshankeji.compose.ui.text.font

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.fontWeight

// copied and adapted from `androidx.compose.ui.text.font.FontWeight`
@Immutable
actual class FontWeight actual constructor(actual val weight: Int) : Comparable<FontWeight> {
    actual override fun compareTo(other: FontWeight): Int =
        weight.compareTo(other.weight)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FontWeight) return false
        return weight == other.weight
    }

    override fun hashCode(): Int =
        weight

    override fun toString(): String =
        "FontWeight(weight=$weight)"

    actual companion object {
        actual val Thin = FontWeight(100)
        actual val ExtraLight = FontWeight(200)
        actual val Light = FontWeight(300)
        actual val Normal = FontWeight(400)
        actual val Medium = FontWeight(500)
        actual val SemiBold = FontWeight(600)
        actual val Bold = FontWeight(700)
        actual val ExtraBold = FontWeight(800)
        actual val Black = FontWeight(900)

        actual val W100 = Thin
        actual val W200 = ExtraLight
        actual val W300 = Light
        actual val W400 = Normal
        actual val W500 = Medium
        actual val W600 = SemiBold
        actual val W700 = Bold
        actual val W800 = ExtraBold
        actual val W900 = Black
    }
}

fun FontWeight.toCssValue(): Int =
    weight

fun StyleScope.applyStyle(fontWeight: FontWeight) {
    fontWeight(fontWeight.toCssValue())
}
