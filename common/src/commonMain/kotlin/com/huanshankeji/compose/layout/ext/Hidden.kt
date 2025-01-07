package com.huanshankeji.compose.layout.ext

import com.huanshankeji.compose.ui.Modifier

expect fun Modifier.hidden(): Modifier

/**
 * An alias for [hidden].
 */
fun Modifier.reserveSpace() = hidden()

/**
 * An alias for [hidden].
 */
fun Modifier.invisible() = hidden()
