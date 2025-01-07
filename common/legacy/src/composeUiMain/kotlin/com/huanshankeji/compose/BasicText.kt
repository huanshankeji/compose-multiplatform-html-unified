package com.huanshankeji.compose

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Element
import com.huanshankeji.compose.ui.ModifierOrAttrs
import com.huanshankeji.compose.ui.toModifier

actual abstract class BasicTextElement : Element()

@Composable
actual fun BasicText(text: String, modifierOrAttrs: ModifierOrAttrs<BasicTextElement>) =
    androidx.compose.foundation.text.BasicText(text, modifierOrAttrs.toModifier())
