package com.huanshankeji.compose.ui

//import androidx.compose.ui.Modifier
import com.huanshankeji.compose.ui.color.Color
import com.huanshankeji.compose.ui.unit.HeightOrWidth
import com.huanshankeji.compose.ui.unit.Length
import com.huanshankeji.compose.ui.unit.LengthOrPercentage

typealias NotNullModifierOrAttrs<TElement> = ModifierOrAttrsScope<TElement>.() -> Unit
typealias ModifierOrAttrs<TElement> = NotNullModifierOrAttrs<TElement>?

@Suppress("NOTHING_TO_INLINE")
inline fun <TElement : Element> modifierOrAttrs(noinline modifierOrAttrs: NotNullModifierOrAttrs<TElement>) =
    modifierOrAttrs

operator fun <TElement : Element> NotNullModifierOrAttrs<TElement>.plus(other: ModifierOrAttrs<TElement>): ModifierOrAttrs<TElement> =
    if (other === null) this
    else {
        {
            this@plus()
            other()
        }
    }


expect abstract class Element


expect class ModifierOrAttrsScope<out TElement : Element> {
    fun style(builder: StyleScope.() -> Unit)
}

/**
 * Keep in mind that the functions in this class call functions in
 * [org.jetbrains.compose.web.css.StyleScope] and [androidx.compose.ui.Modifier] under the hood
 * so their visual results are not consistent.
 * As different orders of `Modifier` function calls produce different results,
 * different orders of function calls in this class produce different results on desktop and Android.
 * They do produce the same results on web as long as no former property is overriden by a latter one,
 * as different orders of CSS properties do in the HTML `style` attribute.
 */
expect class StyleScope {
    fun margin(value: Length)
    fun height(value: HeightOrWidth)
    fun width(value: HeightOrWidth)

    fun backgroundColor(color: Color)

    /**
     * Currently inconsistent, adds inner border on desktop and Android and outer padding on web.
     */
    fun platformBorder(width: Length, color: Color)

    fun outerBorder(width: Length, color: Color)

    fun roundedCornerOuterBorder(width: Length, color: Color, cornerRadius: LengthOrPercentage)
}

fun StyleScope.height(value: LengthOrPercentage) =
    height(HeightOrWidth.Numeric(value))

fun StyleScope.width(value: LengthOrPercentage) =
    width(HeightOrWidth.Numeric(value))

private const val PADDING_MESSAGE =
    "This function is a placeholder for code completion. " +
            "Use `margin` to add space around the composable, which is equivalent to `Modifier.padding`. " +
            "Set `margin` in the inner composable to add inner padding."

@Deprecated(PADDING_MESSAGE)
fun StyleScope.padding(value: LengthOrPercentage): Unit =
    throw NotImplementedError(PADDING_MESSAGE)

/*
/** An alternative immutable design like [Modifier]. */
expect class ModifierOrAttrsImmutable<T : Element> {
    fun padding(): ModifierOrAttrsImmutable<T>
}
*/
