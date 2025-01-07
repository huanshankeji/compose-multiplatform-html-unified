package com.huanshankeji.compose.ui

expect interface Modifier {
    open fun <R> foldIn(initial: R, operation: (R, Element) -> R): R
    open infix fun then(other: Modifier): Modifier
    interface Element : Modifier
    companion object : Modifier
}

/**
 * This serves as a shortcut for code completion and an alternative name when you prefer another type taking the name `Modifier` in a file.
 * In most cases, it's recommended to use [Modifier] directly.
 */
typealias CommonModifier = Modifier
