package com.huanshankeji.compose.material3.ext

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier

@Composable
actual fun RadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier,
    enabled: Boolean,
    idJsDom: String?,
) =
    RadioButton(selected, onClick, modifier.platformModifier, enabled)

@Composable
actual fun RadioButtonRow(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    modifierAfterSelectable: Modifier,
    radioButtonModifier: Modifier,
    enabled: Boolean,
    radioButtonIdJsDom: String,
    label: @Composable () -> Unit,
) =
    Row(
        modifier.platformModifier.selectable(
            selected = selected,
            enabled = enabled,
            role = Role.RadioButton,
            onClick = onClick,
        ).then(modifierAfterSelectable.platformModifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(selected, null, radioButtonModifier.platformModifier) // no need to pass `enabled` here
        Spacer(PlatformModifier.width(16.dp))
        label()
    }

actual fun Modifier.radioGroup(): Modifier =
    platformModify { selectableGroup() }
