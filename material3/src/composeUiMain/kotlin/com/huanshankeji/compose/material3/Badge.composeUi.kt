package com.huanshankeji.compose.material3

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Badge(
    modifier: Modifier,
    content: String
) =
    androidx.compose.material3.Badge(
        modifier.platformModifier
    ) { Text(content) }
