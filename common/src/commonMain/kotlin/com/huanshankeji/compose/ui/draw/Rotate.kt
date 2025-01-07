package com.huanshankeji.compose.ui.draw

import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier

@Stable
expect fun Modifier.rotate(degrees: Float): Modifier
