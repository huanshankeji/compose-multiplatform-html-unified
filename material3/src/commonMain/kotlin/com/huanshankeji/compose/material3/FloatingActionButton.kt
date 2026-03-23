package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExtRecommendedApi
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/floating-action-button/overview
https://developer.android.com/develop/ui/compose/components/fab
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-floating-action-button.html
 */

@ExtRecommendedApi
@Composable
expect fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
)

@ExtRecommendedApi
@Composable
expect fun SmallFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
)

@ExtRecommendedApi
@Composable
expect fun LargeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
)

@ExtRecommendedApi
@Composable
expect fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
)
