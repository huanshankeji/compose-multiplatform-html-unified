package com.huanshankeji.compose.layout

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Element
import com.huanshankeji.compose.ui.ModifierOrAttrs
import com.huanshankeji.compose.ui.toModifier

actual abstract class BoxElement : Element()

actual typealias BoxScope = androidx.compose.foundation.layout.BoxScope

@Composable
actual fun Box(modifierOrAttrs: ModifierOrAttrs<BoxElement>, content: @Composable BoxScope.() -> Unit) =
    androidx.compose.foundation.layout.Box(modifierOrAttrs.toModifier(), content = content)
