package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://developer.android.com/develop/ui/compose/components/tabs
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-primary-tab-row.html
 */

/**
 * Material Design tab row.
 *
 * @see <a href="https://m3.material.io/components/tabs/overview">Material Design tabs</a>
 * @see androidx.compose.material3.PrimaryTabRow
 */
@Composable
expect fun PrimaryTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit,
)

@Composable
expect fun SecondaryTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit,
)

@Composable
expect fun PrimaryScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit,
)

@Composable
expect fun SecondaryScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit,
)

// Tabs are in the `ext` package.
