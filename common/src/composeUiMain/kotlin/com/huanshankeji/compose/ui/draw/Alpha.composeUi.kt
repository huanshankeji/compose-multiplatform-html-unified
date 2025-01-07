package com.huanshankeji.compose.ui.draw

import androidx.compose.ui.draw.alpha
import com.huanshankeji.compose.ui.Modifier

actual fun Modifier.alpha(alpha: Float): Modifier =
    platformModify { alpha(alpha) }
