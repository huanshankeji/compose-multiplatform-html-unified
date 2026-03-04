package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdRadio
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import com.varabyte.kobweb.compose.css.disabled
import com.varabyte.kobweb.compose.css.marginInlineStart
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.modifiers.role
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.px
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
    modifierAfterSelectable: Modifier,
    radioButtonModifier: Modifier,
    enabled: Boolean,
    label: @Composable () -> Unit,
) =
    Div(
        modifier.platformModifier
            .attrsModifier {
                if (enabled) onClick { onClick() }
                else disabled()
            }
            .then(modifierAfterSelectable.platformModifier)
            .toAttrs()
    ) {
        MdRadio(
            radioButtonIdJsDom,
            checked = selected.isTrueOrNull(),
            disabled = enabled.isFalseOrNull(),
            attrs = radioButtonModifier.toAttrs()
        )
        Label(radioButtonIdJsDom, {
            style {
                marginInlineStart(16.px)
            }
        }) {
            label()
        }
    }

actual fun Modifier.radioGroup(): Modifier =
    platformModify { role("radiogroup") }
