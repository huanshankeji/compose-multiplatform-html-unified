package com.huanshankeji.compose.foundation

import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.foundation.layout.ext.outerPadding
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

/**
 * For consistency on different platforms, if used with a padding modifier,
 * use with [Modifier.outerPadding] and use after it, because the padding is not within the background.
 * Otherwise, add an outer [Box] and use [background] on it.
 */
expect fun Modifier.background(color: Color): Modifier
