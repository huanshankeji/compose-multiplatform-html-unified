package com.huanshankeji.compose.foundation.layout

import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier

@Stable
expect fun Modifier.width(intrinsicSize: IntrinsicSize): Modifier

@Stable
expect fun Modifier.height(intrinsicSize: IntrinsicSize): Modifier

enum class IntrinsicSize { Min, Max }
