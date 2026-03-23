package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

// https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#RadioButton(kotlin.Boolean,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Boolean,androidx.compose.foundation.interaction.MutableInteractionSource,androidx.compose.material.RadioButtonColors)

// TODO colors
// The spacing between the radio button and the label is not proper or consistent on both platforms. If ever this needs to be fixed, refer to the Material 3 implementation.
@Composable
expect fun RadioRow(
    selected: Boolean,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
)

@Composable
expect fun RadioGroupRow(modifier: Modifier = Modifier, content: @Composable () -> Unit)

@Composable
expect fun RadioGroupColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit)
