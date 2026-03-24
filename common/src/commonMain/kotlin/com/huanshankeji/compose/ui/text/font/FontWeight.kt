package com.huanshankeji.compose.ui.text.font

import androidx.compose.runtime.Immutable

// copied and adapted from `androidx.compose.ui.text.font.FontWeight`
@Immutable
class FontWeight(val weight: Int) : Comparable<FontWeight> {
    override fun compareTo(other: FontWeight): Int =
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

    companion object {
        val Thin = FontWeight(100)
        val ExtraLight = FontWeight(200)
        val Light = FontWeight(300)
        val Normal = FontWeight(400)
        val Medium = FontWeight(500)
        val SemiBold = FontWeight(600)
        val Bold = FontWeight(700)
        val ExtraBold = FontWeight(800)
        val Black = FontWeight(900)

        val W100 = Thin
        val W200 = ExtraLight
        val W300 = Light
        val W400 = Normal
        val W500 = Medium
        val W600 = SemiBold
        val W700 = Bold
        val W800 = ExtraBold
        val W900 = Black
    }
}
