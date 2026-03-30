package com.huanshankeji.compose.ui.text.font

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlin.math.roundToInt
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.fontWeight

// Copied and adapted from `FontWeight` in `androidx.compose.ui.text.font`. Do not edit without referencing to the original corresponding implementation.
@Immutable
actual class FontWeight actual constructor(actual val weight: Int) : Comparable<FontWeight> {

    actual companion object {
        @Stable actual val W100 = FontWeight(100)
        @Stable actual val W200 = FontWeight(200)
        @Stable actual val W300 = FontWeight(300)
        @Stable actual val W400 = FontWeight(400)
        @Stable actual val W500 = FontWeight(500)
        @Stable actual val W600 = FontWeight(600)
        @Stable actual val W700 = FontWeight(700)
        @Stable actual val W800 = FontWeight(800)
        @Stable actual val W900 = FontWeight(900)

        @Stable actual val Thin = W100
        @Stable actual val ExtraLight = W200
        @Stable actual val Light = W300
        @Stable actual val Normal = W400
        @Stable actual val Medium = W500
        @Stable actual val SemiBold = W600
        @Stable actual val Bold = W700
        @Stable actual val ExtraBold = W800
        @Stable actual val Black = W900

        internal val values: List<FontWeight> =
            listOf(W100, W200, W300, W400, W500, W600, W700, W800, W900)
    }

    init {
        // `requirePrecondition` used in Compose UI code
        require(weight in 1..1000) {
            "Font weight can be in range [1, 1000]. Current value: $weight"
        }
    }

    actual override operator fun compareTo(other: FontWeight): Int =
        weight.compareTo(other.weight)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FontWeight) return false
        if (weight != other.weight) return false
        return true
    }

    override fun hashCode(): Int =
        weight

    override fun toString(): String =
        "FontWeight(weight=$weight)"

    fun toCssValue(): Int =
        weight
}

actual fun lerp(start: FontWeight, stop: FontWeight, fraction: Float): FontWeight {
    val weight = androidx.compose.ui.util.lerp(start.weight, stop.weight, fraction).coerceIn(1, 1000)
    return FontWeight(weight)
}

fun StyleScope.applyStyle(fontWeight: FontWeight) {
    fontWeight(fontWeight.toCssValue())
}
