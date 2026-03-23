package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material3.MultiChoiceSegmentedButtonRowScope
import com.huanshankeji.compose.material3.SegmentedButtonDefaultShapeArgs
import com.huanshankeji.compose.material3.SingleChoiceSegmentedButtonRowScope
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/segmented-buttons/overview
https://developer.android.com/develop/ui/compose/components/segmented-button
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-segmented-button.html
 */

/**
 * Material Design segmented button.
 *
 * A segmented button represents a single option in a segmented button row.
 */
@Composable
expect fun SingleChoiceSegmentedButtonRowScope.SegmentedButton(
    selected: Boolean,
    onClick: () -> Unit,
    defaultShapeArgs: SegmentedButtonDefaultShapeArgs,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    label: String, //@Composable () -> Unit
)

/**
 * @see SingleChoiceSegmentedButtonRowScope.SegmentedButton
 */
@Composable
expect fun MultiChoiceSegmentedButtonRowScope.SegmentedButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    defaultShapeArgs: SegmentedButtonDefaultShapeArgs,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    label: String, //@Composable () -> Unit
)

