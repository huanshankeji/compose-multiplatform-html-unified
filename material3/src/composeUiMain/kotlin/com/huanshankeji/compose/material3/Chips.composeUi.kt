package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun AssistChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable (() -> Unit)?
) =
    androidx.compose.material3.AssistChip(
        onClick,
        label,
        modifier.platformModifier,
        enabled,
        leadingIcon
    )

@Composable
actual fun FilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable (() -> Unit)?
) =
    androidx.compose.material3.FilterChip(
        selected,
        onClick,
        label,
        modifier.platformModifier,
        enabled,
        leadingIcon = leadingIcon
    )

@Composable
actual fun InputChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable (() -> Unit)?
) =
    androidx.compose.material3.InputChip(
        selected,
        onClick,
        label,
        modifier.platformModifier,
        enabled,
        leadingIcon = leadingIcon
    )

@Composable
actual fun SuggestionChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable (() -> Unit)?
) =
    androidx.compose.material3.SuggestionChip(
        onClick,
        label,
        modifier.platformModifier,
        enabled,
        icon = icon
    )
