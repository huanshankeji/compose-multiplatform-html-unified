package com.huanshankeji.compose.ui.draw

import com.huanshankeji.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.opacity

actual fun Modifier.alpha(alpha: Float): Modifier =
    platformModify { opacity(alpha) }
