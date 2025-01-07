package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material2.CommonSwitch
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun SwitchWithLabel(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    label: String,
    modifier: Modifier,
    enabled: Boolean
) =
    CommonSwitch(checked, onCheckedChange, label, modifier, enabled)