package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean
) =
    androidx.compose.material3.Switch(checked, onCheckedChange, modifier.platformModifier, enabled = enabled)
