package com.huanshankeji.compose.material3.labs

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design segmented button (labs component).
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 *
 * @see <a href="https://m3.material.io/components/segmented-buttons/overview">Material Design segmented buttons</a>
 */
@Composable
expect fun SegmentedButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    label: @Composable () -> Unit
)

/**
 * Material Design segmented button set (labs component).
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 *
 * @see <a href="https://m3.material.io/components/segmented-buttons/overview">Material Design segmented buttons</a>
 */
@Composable
expect fun SegmentedButtonSet(
    modifier: Modifier = Modifier,
    multiselect: Boolean = false,
    content: @Composable () -> Unit
)
