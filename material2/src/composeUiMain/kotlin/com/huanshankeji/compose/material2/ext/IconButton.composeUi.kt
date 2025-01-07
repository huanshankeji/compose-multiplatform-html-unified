package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material2.Icon
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    icon: Icon,
    contentDescription: String?
) =
    com.huanshankeji.compose.material2.IconButton(onClick, modifier) { Icon(icon, contentDescription) }
