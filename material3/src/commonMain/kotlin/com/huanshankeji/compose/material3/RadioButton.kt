package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design radio button.
 *
 * @see <a href="https://m3.material.io/components/radio-button/overview">Material Design radio button</a>
 * @see androidx.compose.material3.RadioButton
 */
@Composable
expect fun RadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
)
