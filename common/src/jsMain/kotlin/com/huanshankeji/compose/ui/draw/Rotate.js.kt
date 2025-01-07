package com.huanshankeji.compose.ui.draw

import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
import com.varabyte.kobweb.compose.css.rotate
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.css.deg

@Stable
actual fun Modifier.rotate(degrees: Float): Modifier =
    platformModify { styleModifier { rotate(degrees.deg) } }
