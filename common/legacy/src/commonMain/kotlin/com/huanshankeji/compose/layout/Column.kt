package com.huanshankeji.compose.layout

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Element
import com.huanshankeji.compose.ui.ModifierOrAttrs

expect abstract class ColumnElement : Element
expect interface ColumnScope

@Composable
expect fun Column(modifierOrAttrs: ModifierOrAttrs<ColumnElement> = null, content: @Composable ColumnScope.() -> Unit)
