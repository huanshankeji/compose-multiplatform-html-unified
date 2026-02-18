package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design assist chip.
 *
 * @see <a href="https://m3.material.io/components/chips/overview">Material Design chips</a>
 * @see androidx.compose.material3.AssistChip
 */
@Composable
expect fun AssistChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null
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
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null
)

/**
 * Material Design input chip.
 *
 * @see <a href="https://m3.material.io/components/chips/overview">Material Design chips</a>
 * @see androidx.compose.material3.InputChip
 */
@Composable
expect fun InputChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null
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
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null
)
