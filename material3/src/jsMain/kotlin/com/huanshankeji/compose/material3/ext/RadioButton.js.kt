package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.layout.ext.outerPadding
import com.huanshankeji.compose.html.material3.MdRadio
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import com.varabyte.kobweb.compose.css.disabled
import com.varabyte.kobweb.compose.ui.modifiers.role
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Label

@Composable
actual fun RadioButton(
    idJsDom: String?,
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier,
    enabled: Boolean,
) =
    MdRadio(
        checked = selected.isTrueOrNull(),
        disabled = enabled.isFalseOrNull(),
        attrs = modifier.toAttrs {
            onClick?.let { onClick { it() } }
            // or use `input` and `change` events from https://github.com/material-components/material-web/blob/main/docs/components/radio.md#events?
        }
    )


@Composable
actual fun RadioButtonRow(
    radioButtonIdJsDom: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    radioButtonModifier: Modifier,
    conventionalPadding: Dp?,
    enabled: Boolean,
    content: @Composable () -> Unit,
) =
    // https://github.com/material-components/material-web/blob/main/docs/components/radio.md#accessibility
    Div(
        modifier
            .run {
                if (conventionalPadding !== null) outerPadding(horizontal = conventionalPadding) else this
            }
            .toAttrs {
                if (enabled) onClick { onClick() }
                else disabled()
            }) {
        MdRadio(
            radioButtonIdJsDom,
            checked = selected.isTrueOrNull(),
            disabled = enabled.isFalseOrNull(),
            attrs = radioButtonModifier.toAttrs()
        )
        Label(
            radioButtonIdJsDom,
            if (conventionalPadding !== null) Modifier.outerPadding(start = conventionalPadding).toAttrs() else null
        ) {
            content()
        }
    }

actual fun Modifier.radioGroup(): Modifier =
    platformModify { role("radiogroup") }
