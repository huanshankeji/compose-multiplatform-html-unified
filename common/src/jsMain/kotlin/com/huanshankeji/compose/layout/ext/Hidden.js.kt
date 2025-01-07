package com.huanshankeji.compose.layout.ext

import com.huanshankeji.compose.ui.Modifier
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.ui.modifiers.visibility

actual fun Modifier.hidden(): Modifier =
    platformModify { visibility(Visibility.Hidden) }
