package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier


/**
 * Material Design tab.
 *
 * @see <a href="https://m3.material.io/components/tabs/overview">Material Design tabs</a>
 * @see androidx.compose.material3.Tab
 */
@Composable
expect fun PrimaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable ((Modifier) -> Unit)? = null,
)

@Composable
expect fun SecondaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable ((Modifier) -> Unit)? = null,
)
