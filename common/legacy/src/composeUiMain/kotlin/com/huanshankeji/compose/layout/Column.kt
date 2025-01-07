package com.huanshankeji.compose.layout

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Element
import com.huanshankeji.compose.ui.ModifierOrAttrs
import com.huanshankeji.compose.ui.toModifier


actual abstract class ColumnElement : Element()
actual typealias ColumnScope = androidx.compose.foundation.layout.ColumnScope

@Composable
actual fun Column(modifierOrAttrs: ModifierOrAttrs<ColumnElement>, content: @Composable ColumnScope.() -> Unit) =
    androidx.compose.foundation.layout.Column(modifierOrAttrs.toModifier(), content = content)
