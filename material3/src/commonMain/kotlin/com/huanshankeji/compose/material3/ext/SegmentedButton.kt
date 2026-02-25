package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material3.Icon
import com.huanshankeji.compose.material3.MultiChoiceSegmentedButtonRowScope
import com.huanshankeji.compose.material3.SegmentedButtonDefaultShapeArgs
import com.huanshankeji.compose.material3.SingleChoiceSegmentedButtonRowScope
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design segmented button.
 *
 * A segmented button represents a single option in a segmented button row.
 *
 * @param selected whether this segmented button is selected
 * @param onClick called when this segmented button is clicked
 * @param modifier the [Modifier] to be applied to this segmented button
 * @param enabled controls the enabled state of this segmented button
 * @param icon optional icon for this segmented button, typically an [Icon]
 * @param label the label content for this segmented button
 */
@Composable
expect fun SingleChoiceSegmentedButtonRowScope.SegmentedButton(
    selected: Boolean,
    onClick: () -> Unit,
    defaultShapeArgs: SegmentedButtonDefaultShapeArgs,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    label: String //@Composable () -> Unit
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
    label: String //@Composable () -> Unit
)

