package com.huanshankeji.compose.ui.graphics

import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
expect class Color {
    override fun toString(): String

    companion object {
        @Stable
        val Black: Color

        @Stable
        val DarkGray: Color

        @Stable
        val Gray: Color

        @Stable
        val LightGray: Color

        @Stable
        val White: Color

        @Stable
        val Red: Color

        @Stable
        val Green: Color

        @Stable
        val Blue: Color

        @Stable
        val Yellow: Color

        @Stable
        val Cyan: Color

        @Stable
        val Magenta: Color

        @Stable
        val Transparent: Color

        /*
        // consider using CSS "inherit", "initial", "unset" for this
        @Stable
        val Unspecified: Color
        */

        /*
        fun hsv(
            hue: Float,
            saturation: Float,
            value: Float,
            alpha: Float = 1f,
            //colorSpace: Rgb = ColorSpaces.Srgb
        ): Color
        */

        fun hsl(
            hue: Float,
            saturation: Float,
            lightness: Float,
            alpha: Float = 1f,
            //colorSpace: Rgb = ColorSpaces.Srgb
        ): Color
    }
}

@Stable
expect fun Color(
    red: Float,
    green: Float,
    blue: Float,
    alpha: Float = 1f,
    //colorSpace: ColorSpace = ColorSpaces.Srgb
): Color

/** ARGB */
@Stable
expect fun Color(@ColorInt color: Int): Color

@Stable
expect fun Color(color: Long): Color

@Stable
expect fun Color(
    @IntRange(from = 0, to = 0xFF) red: Int,
    @IntRange(from = 0, to = 0xFF) green: Int,
    @IntRange(from = 0, to = 0xFF) blue: Int,
    @IntRange(from = 0, to = 0xFF) alpha: Int = 0xFF
): Color

/*
@Stable
expect fun lerp(start: Color, stop: Color, @FloatRange(from = 0.0, to = 1.0) fraction: Float): Color

@Stable
expect fun Color.compositeOver(background: Color): Color
*/

@Stable
expect fun Color.luminance(): Float

@Stable
@ColorInt
expect fun Color.toArgb(): Int

fun interface ColorProducer {
    operator fun invoke(): Color
}
