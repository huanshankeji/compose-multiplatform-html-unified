package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Stable
import com.huanshankeji.compose.foundation.layout.fillMaxHeight
import com.huanshankeji.compose.foundation.layout.fillMaxSize
import com.huanshankeji.compose.foundation.layout.fillMaxWidth
import com.huanshankeji.compose.ui.Modifier

/**
 * Similar to [fillMaxWidth] but delegates to the `stretch` CSS value on JS DOM.
 * See https://developer.mozilla.org/en-US/docs/Web/CSS/width#stretch.
 */
@Stable
expect fun Modifier.fillMaxWidthStretch(): Modifier

/**
 * Similar to [fillMaxHeight] but delegates to the `stretch` CSS value on JS DOM.
 */
@Stable
expect fun Modifier.fillMaxHeightStretch(): Modifier

/**
 * Similar to [fillMaxSize] but delegates to the `stretch` CSS value on JS DOM.
 */
@Stable
expect fun Modifier.fillMaxSizeStretch(): Modifier
