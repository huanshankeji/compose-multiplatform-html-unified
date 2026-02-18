package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design tab row.
 *
 * @see <a href="https://m3.material.io/components/tabs/overview">Material Design tabs</a>
 * @see androidx.compose.material3.TabRow
 */
@Composable
expect fun TabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit
)

/**
 * Material Design tab.
 *
 * @see <a href="https://m3.material.io/components/tabs/overview">Material Design tabs</a>
 * @see androidx.compose.material3.Tab
 */
@Composable
expect fun Tab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null
)
