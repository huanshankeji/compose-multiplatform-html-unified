package com.huanshankeji.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import com.huanshankeji.compose.ui.color.Color
import com.huanshankeji.compose.ui.unit.*
import com.huanshankeji.compose.ui.unit.HeightOrWidth.*

fun <TElement : Element> ModifierOrAttrs<TElement>.toModifier(): Modifier =
    this?.let {
        ModifierOrAttrsScope<TElement>(Modifier).apply(it).modifier
    } ?: Modifier

fun <TElement : Element> Modifier.toModifierOrAttrs(): ModifierOrAttrs<TElement> =
    if (this !== Modifier) {
        { modify { this then this@toModifierOrAttrs } }
    } else null

actual abstract class Element

actual class ModifierOrAttrsScope<out TElement : Element>(modifier: Modifier) {
    var modifier: Modifier = modifier
        private set

    fun modify(block: Modifier.() -> Modifier) {
        modifier = modifier.block()
    }

    actual fun style(builder: StyleScope.() -> Unit) {
        StyleScope(this).builder()
    }
}

actual class StyleScope(val modifierOrAttrsScope: ModifierOrAttrsScope<*>) {
    fun modify(block: Modifier.() -> Modifier) =
        modifierOrAttrsScope.modify(block)

    actual fun margin(value: Length) = modify {
        padding(value.asDpOrPx().platformValue)
    }

    actual fun height(value: HeightOrWidth) = modify {
        when (value) {
            FitContent -> this //wrapContentHeight()
            FillMax -> fillMaxHeight()
            is Numeric -> height(value.value.asDpOrPx().platformValue)
        }
    }

    actual fun width(value: HeightOrWidth) = modify {
        this@modify.fillMaxWidth()
        when (value) {
            FitContent -> this //wrapContentWidth()
            FillMax -> fillMaxHeight()
            is Numeric -> width(value.value.asDpOrPx().platformValue)
        }
    }


    actual fun backgroundColor(color: Color) = modify {
        background(color.platformValue)
    }

    actual fun platformBorder(width: Length, color: Color) = modify {
        border(width.asDpOrPx().platformValue, color.platformValue)
    }

    actual fun outerBorder(width: Length, color: Color) {
        platformBorder(width, color)
        margin(width)
    }

    actual fun roundedCornerOuterBorder(width: Length, color: Color, cornerRadius: LengthOrPercentage) {
        modify {
            border(
                width.asDpOrPx().platformValue, color.platformValue, when (cornerRadius) {
                    is DpOrPx -> RoundedCornerShape(cornerRadius.platformValue)
                    is Percentage -> RoundedCornerShape(cornerRadius.value)
                    // TODO
                    else -> throw AssertionError()
                }
            )
        }
        margin(width)
    }
}
