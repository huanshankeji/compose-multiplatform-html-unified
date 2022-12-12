package com.huanshankeji.compose.ui

//import androidx.compose.ui.Modifier
import com.huanshankeji.compose.ui.unit.SizeValue

typealias ModifierOrAttrs<TElement> = (ModifierOrAttrsScope<TElement>.() -> Unit)?

expect abstract class Element

expect class ModifierOrAttrsScope<out TElement : Element> {
    fun style(builder: StyleScope.() -> Unit)
}

expect class StyleScope {
    fun padding(value: SizeValue)
}

/*
/** An alternative immutable design like [Modifier]. */
expect class ModifierOrAttrsImmutable<T : Element> {
    fun padding(): ModifierOrAttrsImmutable<T>
}
*/
