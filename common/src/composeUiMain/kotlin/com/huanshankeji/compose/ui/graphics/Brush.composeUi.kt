package com.huanshankeji.compose.ui.graphics

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush as PlatformBrush

@Immutable
actual class Brush(val platformValue: PlatformBrush)