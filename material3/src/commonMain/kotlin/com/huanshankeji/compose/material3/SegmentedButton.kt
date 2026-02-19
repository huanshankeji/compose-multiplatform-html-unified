package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design single-choice segmented button row.
 *
 * A segmented button row provides a set of options where only one can be selected at a time.
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 *
 * @param modifier the [Modifier] to be applied to this segmented button row
 * @param content the content of this segmented button row, typically a sequence of [SegmentedButtonRowScope.SegmentedButton] calls
 *
 * @see <a href="https://m3.material.io/components/segmented-buttons/overview">Material Design segmented buttons</a>
 * @see <a href="https://developer.android.com/develop/ui/compose/components/segmented-button">Compose segmented buttons</a>
 */
@Composable
expect fun SingleChoiceSegmentedButtonRow(
    modifier: Modifier = Modifier,
    content: @Composable SingleChoiceSegmentedButtonRowScope.() -> Unit
)

/**
 * Material Design multi-choice segmented button row.
 *
 * A segmented button row provides a set of options where multiple can be selected at the same time.
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 *
 * @param modifier the [Modifier] to be applied to this segmented button row
 * @param content the content of this segmented button row, typically a sequence of [SegmentedButtonRowScope.SegmentedButton] calls
 *
 * @see <a href="https://m3.material.io/components/segmented-buttons/overview">Material Design segmented buttons</a>
 * @see <a href="https://developer.android.com/develop/ui/compose/components/segmented-button">Compose segmented buttons</a>
 */
@Composable
expect fun MultiChoiceSegmentedButtonRow(
    modifier: Modifier = Modifier,
    content: @Composable MultiChoiceSegmentedButtonRowScope.() -> Unit
)

/**
 * Scope for the content of a single-choice segmented button row.
 */
expect class SingleChoiceSegmentedButtonRowScope {
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
    fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        icon: @Composable (() -> Unit)? = null,
        label: @Composable () -> Unit
    )
}

/**
 * Scope for the content of a multi-choice segmented button row.
 */
expect class MultiChoiceSegmentedButtonRowScope {
    @Composable
    fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        icon: @Composable (() -> Unit)? = null,
        label: @Composable () -> Unit
    )
}
