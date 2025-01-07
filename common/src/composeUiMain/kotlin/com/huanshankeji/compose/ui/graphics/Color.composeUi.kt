package com.huanshankeji.compose.ui.graphics

import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.Color as PlatformColor

@Immutable
actual /*value*/ class Color(val platformValue: PlatformColor) {
    actual override fun toString(): String =
        platformValue.toString()

    actual companion object {
        @Stable
        actual val Black: Color = Color(PlatformColor.Black)

        @Stable
        actual val DarkGray: Color = Color(PlatformColor.DarkGray)

        @Stable
        actual val Gray: Color = Color(PlatformColor.Gray)

        @Stable
        actual val LightGray: Color = Color(PlatformColor.LightGray)

        @Stable
        actual val White: Color = Color(PlatformColor.White)

        @Stable
        actual val Red: Color = Color(PlatformColor.Red)

        @Stable
        actual val Green: Color = Color(PlatformColor.Green)

        @Stable
        actual val Blue: Color = Color(PlatformColor.Blue)

        @Stable
        actual val Yellow: Color = Color(PlatformColor.Yellow)

        @Stable
        actual val Cyan: Color = Color(PlatformColor.Cyan)

        @Stable
        actual val Magenta: Color = Color(PlatformColor.Magenta)

        @Stable
        actual val Transparent: Color = Color(PlatformColor.Transparent)

        actual fun hsl(
            hue: Float,
            saturation: Float,
            lightness: Float,
            alpha: Float
        ): Color =
            Color(PlatformColor.hsl(hue, saturation, lightness, alpha))
    }
}

@Stable
actual fun Color(
    red: Float,
    green: Float,
    blue: Float,
    alpha: Float
): Color =
    Color(PlatformColor(red, green, blue, alpha))

@Stable
actual fun Color(@ColorInt color: Int): Color =
    Color(PlatformColor(color))

@Stable
actual fun Color(color: Long): Color =
    Color(PlatformColor(color))

@Stable
actual fun Color(
    @IntRange(from = 0, to = 0xFF) red: Int,
    @IntRange(from = 0, to = 0xFF) green: Int,
    @IntRange(from = 0, to = 0xFF) blue: Int,
    @IntRange(from = 0, to = 0xFF) alpha: Int
): Color =
    Color(PlatformColor(red, green, blue, alpha))

@Stable
actual fun Color.luminance(): Float =
    platformValue.luminance()

@ColorInt
@Stable
actual fun Color.toArgb(): Int =
    platformValue.toArgb()


/**
 * Converts a nullable [Color] to [PlatformColor].
 */
fun Color?.toPlatformValue() =
    this?.platformValue ?: PlatformColor.Unspecified

fun ColorProducer.toPlatformValue() =
    androidx.compose.ui.graphics.ColorProducer {
        this().platformValue
    }

fun ColorProducer?.toNullablePlatformValue() =
    this?.toPlatformValue()
