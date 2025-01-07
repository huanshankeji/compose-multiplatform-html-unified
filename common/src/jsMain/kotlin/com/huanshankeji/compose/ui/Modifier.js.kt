package com.huanshankeji.compose.ui

import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.attributes.AttrsScope
import org.w3c.dom.Element
import com.varabyte.kobweb.compose.ui.Modifier as PlatformModifier

typealias PlatformModifier = PlatformModifier

//actual typealias Modifier = com.varabyte.kobweb.compose.ui.Modifier // this doesn't work
actual interface Modifier {
    val platformModifier: PlatformModifier

    actual fun <R> foldIn(initial: R, operation: (R, Element) -> R): R =
        platformModifier.fold(initial) { r, e -> operation(r, Element.Impl(e)) }

    actual infix fun then(other: Modifier): Modifier =
        Impl(platformModifier.then(other.platformModifier))

    fun platformModify(block: PlatformModifier.() -> PlatformModifier): Modifier =
        Impl(platformModifier.block())

    value class Impl(override val platformModifier: PlatformModifier) : Modifier

    actual interface Element : Modifier {
        val platformElement: PlatformModifier.Element

        value class Impl(override val platformElement: PlatformModifier.Element) : Element {
            override val platformModifier get() = platformElement
        }
    }

    actual companion object : Modifier {
        override val platformModifier = PlatformModifier
    }
}

fun PlatformModifier.toCommonModifier() =
    Modifier.Impl(this)

fun <A : AttrsScope<Element>> Modifier.toAttrs(finalHandler: (A.() -> Unit)? = null): A.() -> Unit =
    platformModifier.toAttrs(finalHandler)
