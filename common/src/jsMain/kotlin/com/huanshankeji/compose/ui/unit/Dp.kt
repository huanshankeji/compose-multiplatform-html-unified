package com.huanshankeji.compose.ui.unit

import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.web.css.CSSpxValue
import org.jetbrains.compose.web.css.px

/**
 * A [Dp] value is mapped to a [CSSpxValue] directly for now.
 * A [CSSpxValue] can be considered density-dependent because a web page can be zoomed.
 * If you think this behavior is inappropriate, please file an issue with your reasons.
 */
fun Dp.toPx(): CSSpxValue =
    value.px
