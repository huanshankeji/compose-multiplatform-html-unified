package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.ui.Modifier

@ExperimentalApi
fun SelectTextFieldArgs.toExposedDropdownMenuBoxTextFieldArgs() =
    ExposedDropdownMenuBoxTextFieldArgs(
        valueComposeUi, onValueChangeComposeUi, enabled, true, true, label, supportingText, isError
    )

@ExperimentalApi
fun SelectMenuArgs.toComposeUiExposedDropdownMenuArgs() =
    ExposedDropdownMenuArgs(
        expandedComposeUi,
        onDismissRequestComposeUi,
        onCloseJsDom,
        Modifier,
        matchAnchorWidth ?: true,
        content
    )

@Composable
actual fun FilledSelect(
    expandedComposeUi: Boolean,
    onExpandedChangeComposeUi: (Boolean) -> Unit,
    @Suppress("UNUSED_PARAMETER") valueJsDom: String,
    modifier: Modifier,
    textFieldArgs: SelectTextFieldArgs,
    menuArgs: SelectMenuArgs
) =
    ExposedDropdownMenuBoxWithFilledTextField(
        expandedComposeUi,
        onExpandedChangeComposeUi,
        modifier,
        textFieldArgs.toExposedDropdownMenuBoxTextFieldArgs(),
        menuArgs.toComposeUiExposedDropdownMenuArgs()
    )

@Composable
actual fun OutlinedSelect(
    expandedComposeUi: Boolean,
    onExpandedChangeComposeUi: (Boolean) -> Unit,
    @Suppress("UNUSED_PARAMETER") valueJsDom: String,
    modifier: Modifier,
    textFieldArgs: SelectTextFieldArgs,
    menuArgs: SelectMenuArgs
) =
    ExposedDropdownMenuBoxWithOutlinedTextField(
        expandedComposeUi,
        onExpandedChangeComposeUi,
        modifier,
        textFieldArgs.toExposedDropdownMenuBoxTextFieldArgs(),
        menuArgs.toComposeUiExposedDropdownMenuArgs()
    )

@Composable
actual fun SelectOption(
    text: @Composable ((Modifier) -> Unit),
    onClick: () -> Unit,
    selectedJsDom: Boolean,
    modifier: Modifier,
    leadingIcon: @Composable ((Modifier) -> Unit)?,
    trailingIcon: @Composable ((Modifier) -> Unit)?,
    enabled: Boolean,
    valueJsDom: String?
) =
    CommonDropdownMenuItem(text, onClick, modifier, leadingIcon, trailingIcon, enabled)
