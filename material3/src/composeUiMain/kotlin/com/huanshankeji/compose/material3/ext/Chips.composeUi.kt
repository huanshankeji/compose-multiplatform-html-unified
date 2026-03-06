package com.huanshankeji.compose.material3.ext

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ext.toNullableContentWithoutModifier
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier

@Composable
actual fun AssistChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?
) =
    AssistChip(
        onClick,
        { Text(label) },
        modifier.platformModifier,
        enabled,
        leadingIcon.toNullableContentWithoutModifier()
    )

@Composable
actual fun ElevatedAssistChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?
) =
    ElevatedAssistChip(
        onClick,
        { Text(label) },
        modifier.platformModifier,
        enabled,
        leadingIcon.toNullableContentWithoutModifier()
    )

@Composable
actual fun FilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?
) =
    FilterChip(
        selected,
        onClick,
        { Text(label) },
        modifier.platformModifier,
        enabled,
        leadingIcon = leadingIcon.toNullableContentWithoutModifier()
    )

@Composable
actual fun ElevatedFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?
) =
    ElevatedFilterChip(
        selected,
        onClick,
        { Text(label) },
        modifier.platformModifier,
        enabled,
        leadingIcon = leadingIcon.toNullableContentWithoutModifier()
    )

@Composable
actual fun InputChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?,
    avatar: @Composable ((Modifier) -> Unit)?,
    // Also consider using `Option` from Arrow.
    showTrailingCloseIconComposeUi: Boolean,
    onRemove: (() -> Unit)?,
) =
    InputChip(
        selected,
        onClick,
        { Text(label) },
        modifier.platformModifier,
        enabled,
        leadingIcon = leadingIcon.toNullableContentWithoutModifier(),
        trailingIcon = if (showTrailingCloseIconComposeUi) {
            {
                Icon(
                    Icons.Default.Close,
                    null,
                    onRemove?.let { PlatformModifier.clickable(onClick = it) }
                        ?: PlatformModifier
                ) // The API might change if this module no longer depends on the icons module in the future.
            }
        } else null,
        avatar = avatar.toNullableContentWithoutModifier()
    )

@Composable
actual fun SuggestionChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?
) =
    SuggestionChip(
        onClick,
        { Text(label) },
        modifier.platformModifier,
        enabled,
        icon = icon.toNullableContentWithoutModifier()
    )

@Composable
actual fun ElevatedSuggestionChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?
) =
    ElevatedSuggestionChip(
        onClick,
        { Text(label) },
        modifier.platformModifier,
        enabled,
        icon = icon.toNullableContentWithoutModifier()
    )
