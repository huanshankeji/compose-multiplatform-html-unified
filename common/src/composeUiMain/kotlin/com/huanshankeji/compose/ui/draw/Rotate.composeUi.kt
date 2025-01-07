package com.huanshankeji.compose.ui.draw

import androidx.compose.runtime.Stable
import androidx.compose.ui.draw.rotate
import com.huanshankeji.compose.ui.Modifier

@Stable
actual fun Modifier.rotate(degrees: Float): Modifier =
    platformModify { rotate(degrees) }
