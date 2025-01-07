package com.huanshankeji.compose.layout

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Element
import com.huanshankeji.compose.ui.ModifierOrAttrs

expect abstract class BoxElement : Element

expect interface BoxScope

@Composable
expect fun Box(modifierOrAttrs: ModifierOrAttrs<BoxElement> = null, content: @Composable BoxScope.() -> Unit)
