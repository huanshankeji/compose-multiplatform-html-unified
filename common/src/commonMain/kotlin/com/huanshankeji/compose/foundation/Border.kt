package com.huanshankeji.compose.foundation

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

@Stable
expect fun Modifier.border(border: BorderStroke): Modifier

@Stable
expect fun Modifier.border(width: Dp, color: Color): Modifier

/*
// There is a `Shape` parameter without a default argument
@Stable
expect fun Modifier.border(width: Dp, brush: Brush): Modifier
*/
