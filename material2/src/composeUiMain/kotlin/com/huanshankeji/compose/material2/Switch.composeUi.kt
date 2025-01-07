package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

// https://developer.android.com/develop/ui/compose/components/switch

@Composable
actual fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean
) =
    androidx.compose.material.Switch(checked, onCheckedChange, modifier.platformModifier, enabled)
