package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun SwitchWithLabel(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
)
