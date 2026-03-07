package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Icon(
    icon: Icon, contentDescription: String?, modifier: Modifier
) =
    androidx.compose.material.Icon(icon, contentDescription, modifier.platformModifier)
