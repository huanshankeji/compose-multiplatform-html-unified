package com.huanshankeji.compose.ui.graphics

import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.varabyte.kobweb.compose.ui.graphics.luminance
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.color
import com.varabyte.kobweb.compose.ui.graphics.Color as PlatformColor
import com.varabyte.kobweb.compose.ui.graphics.Colors as PlatformColors

@Immutable
actual /*value*/ class Color(val platformValue: PlatformColor) {
    actual override fun toString(): String =
        platformValue.toString()

    actual companion object {
        @Stable
        actual val Black: Color = Color(PlatformColors.Black)

        @Stable
        actual val DarkGray: Color = Color(PlatformColors.DarkGray)

        @Stable
        actual val Gray: Color = Color(PlatformColors.Gray)

        @Stable
        actual val LightGray: Color = Color(PlatformColors.LightGray)

        @Stable
        actual val White: Color = Color(PlatformColors.White)

        @Stable
        actual val Red: Color = Color(PlatformColors.Red)

        // The CSS green is #008000 which differs from the Compose one.
        @Stable
        actual val Green: Color = Color(PlatformColor.rgb(0x00, 0xFF, 0x00))

        @Stable
        actual val Blue: Color = Color(PlatformColors.Blue)

        @Stable
        actual val Yellow: Color = Color(PlatformColors.Yellow)

        @Stable
        actual val Cyan: Color = Color(PlatformColors.Cyan)

        @Stable
        actual val Magenta: Color = Color(PlatformColors.Magenta)

        @Stable
        actual val Transparent: Color = Color(PlatformColors.Transparent)

        actual fun hsl(
            hue: Float,
            saturation: Float,
            lightness: Float,
            alpha: Float
        ): Color =
            Color(PlatformColor.hsla(hue, saturation, lightness, alpha))
    }
}

@Stable
actual fun Color(red: Float, green: Float, blue: Float, alpha: Float): Color =
    Color(PlatformColor.rgba(red, green, blue, alpha))

@Stable
actual fun Color(@ColorInt color: Int): Color =
    Color(PlatformColor.argb(color))

@Stable
actual fun Color(color: Long): Color =
    Color(PlatformColor.argb(color))

@Stable
actual fun Color(
    @IntRange(from = 0, to = 0xFF) red: Int,
    @IntRange(from = 0, to = 0xFF) green: Int,
    @IntRange(from = 0, to = 0xFF) blue: Int,
    @IntRange(from = 0, to = 0xFF) alpha: Int
): Color =
    Color(PlatformColor.rgba(red, green, blue, alpha))

@Stable
actual fun Color.luminance(): Float =
    platformValue.luminance

@ColorInt
@Stable
actual fun Color.toArgb(): Int =
    platformValue.toRgb().value

fun StyleScope.applyStyle(color: ColorProducer) {
    color(color().platformValue)
}


// TODO context receivers
fun Modifier.toAttrsWithColor(color: Color?): AttrsScope<*>.() -> Unit =
    toAttrs(color?.let {
        {
            style {
                color(it.platformValue)
            }
        }
    })

/*
fun ColorProducer.toStyle(): StyleScope.() -> Unit =
    {
        color(this@toStyle().platformValue)
    }
*/

// TODO context receivers
fun AttrsScope<*>.applyAttrs(color: ColorProducer) {
    style {
        applyStyle(color)
    }
}

/*
fun ColorProducer.toAttrs(): AttrsScope<*>.() -> Unit =
    {
        style {
            toStyle()()
        }
    }
*/


fun ColorProducer?.toNullableAttrs(): (AttrsScope<*>.() -> Unit)? =
    this?.let { { applyAttrs(it) } }

// TODO context receivers
fun Modifier.toAttrsWithColor(color: ColorProducer?): AttrsScope<*>.() -> Unit =
    toAttrs(color.toNullableAttrs())
