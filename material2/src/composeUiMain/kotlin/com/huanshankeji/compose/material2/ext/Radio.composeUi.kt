package com.huanshankeji.compose.material2.ext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.semantics.Role
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier

// https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#RadioButton(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.material.RadioButtonColors)

@Composable
actual fun RadioRow(selected: Boolean, label: String, onClick: () -> Unit, modifier: Modifier, enabled: Boolean) =
    Row(Modifier.platformModifier.selectable(selected, enabled, Role.RadioButton, onClick)) {
        RadioButton(selected, null)
        Text(label)
    }

@Composable
actual fun RadioGroupRow(modifier: Modifier, content: @Composable () -> Unit) =
    Row(PlatformModifier.selectableGroup().then(modifier.platformModifier)) { content() }

@Composable
actual fun RadioGroupColumn(modifier: Modifier, content: @Composable () -> Unit) =
    Column(PlatformModifier.selectableGroup().then(modifier.platformModifier)) { content() }