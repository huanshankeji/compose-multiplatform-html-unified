package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Icon,
    contentDescription: String?
)
