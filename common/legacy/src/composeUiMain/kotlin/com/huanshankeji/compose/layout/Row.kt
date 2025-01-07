package com.huanshankeji.compose.layout

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Element
import com.huanshankeji.compose.ui.ModifierOrAttrs
import com.huanshankeji.compose.ui.toModifier

actual abstract class RowElement : Element()
actual typealias RowScope = androidx.compose.foundation.layout.RowScope

@Composable
actual fun Row(modifierOrAttrs: ModifierOrAttrs<RowElement>, content: @Composable RowScope.() -> Unit) =
    androidx.compose.foundation.layout.Row(modifierOrAttrs.toModifier(), content = content)
