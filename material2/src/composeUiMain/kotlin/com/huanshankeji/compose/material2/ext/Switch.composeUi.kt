package com.huanshankeji.compose.material2.ext

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.material2.Switch
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun SwitchWithLabel(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    label: String,
    modifier: Modifier,
    enabled: Boolean
) =
    Row(modifier) {
        Switch(checked, onCheckedChange, enabled = enabled)
        Text(label)
    }
