package com.huanshankeji.compose.ui.graphics

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.web.css.LineStyle

@Immutable
actual class Brush(val lineStyle: LineStyle, val color: Color)