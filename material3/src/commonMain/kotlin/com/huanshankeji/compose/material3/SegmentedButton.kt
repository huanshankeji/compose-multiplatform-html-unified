package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design single-choice segmented button row.
 *
 * A segmented button row provides a set of options where only one can be selected at a time.
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 *
 * @see <a href="https://m3.material.io/components/segmented-buttons/overview">Material Design segmented buttons</a>
 * @see <a href="https://developer.android.com/develop/ui/compose/components/segmented-button">Compose segmented buttons</a>
 */
@Composable
expect fun SingleChoiceSegmentedButtonRow(
    modifier: Modifier = Modifier,
    space: Dp? = null,
    content: @Composable SingleChoiceSegmentedButtonRowScope.() -> Unit
)

/**
 * Material Design multi-choice segmented button row.
 *
 * A segmented button row provides a set of options where multiple can be selected at the same time.
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 *
 * @see <a href="https://m3.material.io/components/segmented-buttons/overview">Material Design segmented buttons</a>
 * @see <a href="https://developer.android.com/develop/ui/compose/components/segmented-button">Compose segmented buttons</a>
 */
@Composable
expect fun MultiChoiceSegmentedButtonRow(
    modifier: Modifier = Modifier,
    space: Dp? = null,
    content: @Composable MultiChoiceSegmentedButtonRowScope.() -> Unit
)

/**
 * For `SegmentedButtonDefaults.itemShape` on Compose UI.
 */
class SegmentedButtonDefaultShapeArgs(val index: Int, val count: Int)

/**
 * Scope for the content of a single-choice segmented button row.
 */
expect class SingleChoiceSegmentedButtonRowScope

/**
 * Scope for the content of a multi-choice segmented button row.
 */
expect class MultiChoiceSegmentedButtonRowScope
