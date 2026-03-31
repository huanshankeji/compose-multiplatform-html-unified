package com.huanshankeji.compose.ui.text.font

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
expect class FontWeight(weight: Int) : Comparable<FontWeight> {
    val weight: Int

    companion object {
        @Stable val W100: FontWeight
        @Stable val W200: FontWeight
        @Stable val W300: FontWeight
        @Stable val W400: FontWeight
        @Stable val W500: FontWeight
        @Stable val W600: FontWeight
        @Stable val W700: FontWeight
        @Stable val W800: FontWeight
        @Stable val W900: FontWeight

        @Stable val Thin: FontWeight
        @Stable val ExtraLight: FontWeight
        @Stable val Light: FontWeight
        @Stable val Normal: FontWeight
        @Stable val Medium: FontWeight
        @Stable val SemiBold: FontWeight
        @Stable val Bold: FontWeight
        @Stable val ExtraBold: FontWeight
        @Stable val Black: FontWeight
    }

    override fun compareTo(other: FontWeight): Int
}

expect fun lerp(start: FontWeight, stop: FontWeight, fraction: Float): FontWeight
