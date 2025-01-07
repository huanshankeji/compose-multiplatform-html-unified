package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier

@Stable
actual fun Modifier.fillMaxWidthStretch(): Modifier =
    platformModify { fillMaxWidth() }

@Stable
actual fun Modifier.fillMaxHeightStretch(): Modifier =
    platformModify { fillMaxHeight() }

@Stable
actual fun Modifier.fillMaxSizeStretch(): Modifier =
    platformModify { fillMaxSize() }
