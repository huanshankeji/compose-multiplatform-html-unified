package com.huanshankeji.compose.ui

import com.huanshankeji.compose.ui.color.Color
import com.huanshankeji.compose.ui.unit.HeightOrWidth
import com.huanshankeji.compose.ui.unit.HeightOrWidth.*
import com.huanshankeji.compose.ui.unit.Length
import com.huanshankeji.compose.ui.unit.LengthOrPercentage
import com.huanshankeji.compose.web.css.FIT_CONTENT
import com.huanshankeji.compose.web.css.height
import com.huanshankeji.compose.web.css.width
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.w3c.dom.HTMLElement

/*
fun <TElement : Element, TAttrsScope : AttrsScope<TElement>> ModifierOrAttrs<TElement>.toAttrs2(): (TAttrsScope.() -> Unit)? =
    this?.let { { ModifierOrAttrsScope(this).it() } }
*/

fun <TElement : Element> ModifierOrAttrs<TElement>.toAttrs(): AttrBuilderContext<TElement>? =
    this?.let { { ModifierOrAttrsScope(this).it() } }

fun <TElement : Element> AttrBuilderContext<TElement>?.toModifierOrAttrs(): ModifierOrAttrs<TElement> =
    this?.let { { attrsScope.it() } }

actual typealias Element = HTMLElement

actual class ModifierOrAttrsScope<out TElement : Element>(val attrsScope: AttrsScope<TElement>) {
    actual fun style(builder: StyleScope.() -> Unit) =
        attrsScope.style { StyleScope(this).builder() }
}

actual class StyleScope(val styleScope: org.jetbrains.compose.web.css.StyleScope) {
    actual fun margin(value: Length) =
        styleScope.margin(value.platformValue)

    fun margin(value: LengthOrPercentage) =
        styleScope.margin(value.platformValue)

    actual fun height(value: HeightOrWidth) =
        styleScope.run {
            when (value) {
                FitContent -> height(FIT_CONTENT)
                FillMax -> height(100.percent)
                is Numeric -> height(value.value.platformValue)
            }
        }

    actual fun width(value: HeightOrWidth) =
        styleScope.run {
            when (value) {
                FitContent -> width(FIT_CONTENT)
                FillMax -> width(100.percent)
                is Numeric -> width(value.value.platformValue)
            }
        }


    actual fun backgroundColor(color: Color) =
        styleScope.backgroundColor(color.platformValue)

    actual fun platformBorder(width: Length, color: Color) =
        styleScope.border(width.platformValue, LineStyle.Solid, color.platformValue)

    actual fun outerBorder(width: Length, color: Color) =
        platformBorder(width, color)

    actual fun roundedCornerOuterBorder(width: Length, color: Color, cornerRadius: LengthOrPercentage) {
        outerBorder(width, color)
        styleScope.borderRadius(cornerRadius.platformValue)
    }
}
