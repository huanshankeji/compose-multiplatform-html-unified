package com.huanshankeji.compose.foundation

import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor

actual fun Modifier.background(color: Color): Modifier =
    platformModify { backgroundColor(color.platformValue) }
