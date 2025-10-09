package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.foundation.background
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.padding
import com.huanshankeji.compose.ui.Modifier

/**
 * See the KDoc of the overload with one [Dp] parameter for platform differences.
 */
@Stable
expect fun Modifier.innerPadding(start: Dp = 0.dp, top: Dp = 0.dp, end: Dp = 0.dp, bottom: Dp = 0.dp): Modifier

/**
 * See the KDoc of the overload with one [Dp] parameter for platform differences.
 */
@Stable
expect fun Modifier.innerPadding(horizontal: Dp = 0.dp, vertical: Dp = 0.dp): Modifier

/**
 * This delegates to the CSS `padding` property on JS DOM and adds the padding inside.
 * For consistency on different platforms, please use it as the **last** modifier **after** the other modifiers such as [background] and [clickable].
 * See the KDoc of the [padding] overload with one [Dp] parameter for more information.
 * @see outerPadding
 */
@Stable
expect fun Modifier.innerPadding(all: Dp): Modifier

/**
 * See the KDoc of the overload with one [Dp] parameter for platform differences.
 */
@Stable
expect fun Modifier.innerPadding(paddingValues: PaddingValues): Modifier

/**
 * See the KDoc of the overload with one [Dp] parameter for platform differences.
 */
@Stable
expect fun Modifier.absoluteInnerPadding(left: Dp = 0.dp, top: Dp = 0.dp, right: Dp = 0.dp, bottom: Dp = 0.dp): Modifier
