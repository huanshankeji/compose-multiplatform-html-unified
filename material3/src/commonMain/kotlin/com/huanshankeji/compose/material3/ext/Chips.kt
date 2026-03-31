package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/chips/overview
https://developer.android.com/develop/ui/compose/components/chip
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-assist-chip.html
 */

/**
 * Material Design assist chip.
 *
 * @see <a href="https://m3.material.io/components/chips/overview">Material Design chips</a>
 * @see androidx.compose.material3.AssistChip
 */
@Composable
expect fun AssistChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable ((Modifier) -> Unit)? = null,
)

/**
 * Material Design elevated assist chip.
 *
 * @see <a href="https://m3.material.io/components/chips/overview">Material Design chips</a>
 * @see androidx.compose.material3.ElevatedAssistChip
 */
@Composable
expect fun ElevatedAssistChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable ((Modifier) -> Unit)? = null,
)

/**
 * Material Design filter chip.
 *
 * @see <a href="https://m3.material.io/components/chips/overview">Material Design chips</a>
 * @see androidx.compose.material3.FilterChip
 */
@Composable
expect fun FilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable ((Modifier) -> Unit)? = null,
)

/**
 * Material Design elevated filter chip.
 *
 * @see <a href="https://m3.material.io/components/chips/overview">Material Design chips</a>
 * @see androidx.compose.material3.ElevatedFilterChip
 */
@Composable
expect fun ElevatedFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable ((Modifier) -> Unit)? = null,
)

/**
 * Material Design input chip.
 *
 * @see <a href="https://m3.material.io/components/chips/overview">Material Design chips</a>
 * @see androidx.compose.material3.InputChip
 * @param avatar In Compose UI, `avatar` shadows `leadingIcon` when specified.
 *
 * Note that the trailing close icon is always shown in JS DOM and always removes the chip when clicked,
 * so you are recommended to wrap this component in an if block and always pass [onRemove] to remove the chip for consistency.
 */
@Composable
expect fun InputChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable ((Modifier) -> Unit)? = null,
    avatar: @Composable ((Modifier) -> Unit)? = null,
    showTrailingCloseIconComposeUi: Boolean = true,
    onRemove: (() -> Unit)? = null,
)

/**
 * Material Design suggestion chip.
 *
 * @see <a href="https://m3.material.io/components/chips/overview">Material Design chips</a>
 * @see androidx.compose.material3.SuggestionChip
 */
@Composable
expect fun SuggestionChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable ((Modifier) -> Unit)? = null,
)

/**
 * Material Design elevated suggestion chip.
 *
 * @see <a href="https://m3.material.io/components/chips/overview">Material Design chips</a>
 * @see androidx.compose.material3.ElevatedSuggestionChip
 */
@Composable
expect fun ElevatedSuggestionChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable ((Modifier) -> Unit)? = null,
)
