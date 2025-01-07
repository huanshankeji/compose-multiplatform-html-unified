package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

/*
// not available on JS
@Composable
expect fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)
*/

