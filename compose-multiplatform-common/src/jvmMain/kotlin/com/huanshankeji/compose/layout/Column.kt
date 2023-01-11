package com.huanshankeji.compose.layout

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Element
import com.huanshankeji.compose.ui.ModifierOrAttrs
import com.huanshankeji.compose.ui.StyleScope
import com.huanshankeji.compose.ui.toModifier

actual abstract class ColumnElement : Element()
actual class ColumnScope(val platformColumnScope: androidx.compose.foundation.layout.ColumnScope) : LinearLayoutScope {
    override fun StyleScope.weight(weight: Float) = modify {
        with(platformColumnScope) { weight(weight) }
    }
}

@Composable
actual fun Column(modifierOrAttrs: ModifierOrAttrs<ColumnElement>, content: @Composable ColumnScope.() -> Unit) =
    androidx.compose.foundation.layout.Column(modifierOrAttrs.toModifier()) {
        ColumnScope(this).content()
    }
