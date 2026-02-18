package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.platform.platformModifier

@Composable
actual fun RadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier,
    enabled: Boolean
) =
    androidx.compose.material3.RadioButton(
        selected,
        onClick,
        modifier.platformModifier,
        enabled
    )
