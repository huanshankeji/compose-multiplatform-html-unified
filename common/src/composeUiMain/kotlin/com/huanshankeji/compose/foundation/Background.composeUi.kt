package com.huanshankeji.compose.foundation

import androidx.compose.foundation.background
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

actual fun Modifier.background(color: Color): Modifier =
    platformModify { background(color.platformValue) }
