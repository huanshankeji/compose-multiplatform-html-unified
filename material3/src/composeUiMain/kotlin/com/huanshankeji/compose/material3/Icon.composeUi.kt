package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Icon(
    icon: Icon,
    contentDescription: String?,
    modifier: Modifier
) =
    androidx.compose.material3.Icon(icon, contentDescription, modifier.platformModifier)
