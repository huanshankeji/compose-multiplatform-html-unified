package com.huanshankeji.compose.material3.ext

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier

/*
https://developer.android.com/develop/ui/compose/components/radio-button
https://developer.android.com/develop/ui/compose/components/radio-button#create-basic
 */

@Composable
actual fun RadioButton(
    idJsDom: String?,
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier,
    enabled: Boolean,
) =
    RadioButton(selected, onClick, modifier.platformModifier, enabled)


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
    Row(
        modifier.platformModifier.selectable(
            selected = selected,
            enabled = enabled,
            role = Role.RadioButton,
            onClick = onClick
        ).run {
            if (conventionalPadding !== null) padding(horizontal = conventionalPadding) else this
        }
    ) {
        RadioButton(selected, null, radioButtonModifier.platformModifier) // no need to pass `enabled` here
        if (conventionalPadding !== null)
            Box(PlatformModifier.padding(start = conventionalPadding)) { content() }
        else
            content()
    }

actual fun Modifier.radioGroup(): Modifier =
    platformModify { selectableGroup() }
