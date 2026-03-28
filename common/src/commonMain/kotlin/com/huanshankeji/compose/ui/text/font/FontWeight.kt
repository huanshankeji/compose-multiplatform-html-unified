package com.huanshankeji.compose.ui.text.font

import androidx.compose.runtime.Immutable

@Immutable
expect class FontWeight(weight: Int) : Comparable<FontWeight> {
    val weight: Int

    override fun compareTo(other: FontWeight): Int

    companion object {
        val Thin: FontWeight
        val ExtraLight: FontWeight
        val Light: FontWeight
        val Normal: FontWeight
        val Medium: FontWeight
        val SemiBold: FontWeight
        val Bold: FontWeight
        val ExtraBold: FontWeight
        val Black: FontWeight

        val W100: FontWeight
        val W200: FontWeight
        val W300: FontWeight
        val W400: FontWeight
        val W500: FontWeight
        val W600: FontWeight
        val W700: FontWeight
        val W800: FontWeight
        val W900: FontWeight
    }
}
