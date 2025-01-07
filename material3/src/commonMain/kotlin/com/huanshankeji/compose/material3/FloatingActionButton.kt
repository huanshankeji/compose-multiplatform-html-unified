package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExtRecommendedApi
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.ui.Modifier

@ExtRecommendedApi
@Composable
expect fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

@ExtRecommendedApi
@Composable
expect fun SmallFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

@ExtRecommendedApi
@Composable
expect fun LargeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

@ExtRecommendedApi
@Composable
expect fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
)
