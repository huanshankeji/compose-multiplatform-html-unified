package com.huanshankeji.compose.foundation.text

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.ColorProducer

@Deprecated(
    "This is not needed as there in a `TaglessBasicText` composable. Use the one below.",
    level = DeprecationLevel.HIDDEN
)
@Composable
fun BasicText(text: String) =
    BasicText(text, Modifier, null)

@Composable
expect fun BasicText(text: String, modifier: Modifier = Modifier, color: ColorProducer? = null)
