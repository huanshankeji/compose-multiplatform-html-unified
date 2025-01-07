package com.huanshankeji.compose.ui

import kotlin.jvm.JvmInline
import androidx.compose.ui.Modifier as PlatformModifier

typealias PlatformModifier = PlatformModifier

//actual typealias Modifier = androidx.compose.ui.Modifier // this doesn't work
actual interface Modifier {
    val platformModifier: PlatformModifier

    actual fun <R> foldIn(initial: R, operation: (R, Element) -> R): R =
        platformModifier.foldIn(initial) { r, e -> operation(r, Element.Impl(e)) }

    actual infix fun then(other: Modifier): Modifier =
        Impl(platformModifier.then(other.platformModifier))

    fun platformModify(block: PlatformModifier.() -> PlatformModifier): Modifier =
        Impl(platformModifier.block())

    @JvmInline
    value class Impl(override val platformModifier: PlatformModifier) : Modifier

    actual interface Element : Modifier {
        val platformElement: PlatformModifier.Element

        @JvmInline
        value class Impl(override val platformElement: PlatformModifier.Element) : Element {
            override val platformModifier get() = platformElement
        }
    }

    actual companion object : Modifier {
        override val platformModifier = PlatformModifier
    }
}