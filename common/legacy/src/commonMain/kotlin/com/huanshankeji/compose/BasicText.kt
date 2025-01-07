package com.huanshankeji.compose

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Element
import com.huanshankeji.compose.ui.ModifierOrAttrs

expect abstract class BasicTextElement : Element

@Composable
expect fun BasicText(text: String, modifierOrAttrs: ModifierOrAttrs<BasicTextElement> = null)
