package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.platform.platformModifier

@Composable
actual fun HorizontalDivider(
    modifier: Modifier
) =
    androidx.compose.material3.HorizontalDivider(
        modifier.platformModifier
    )
