package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun Icon(
    icon: Icon,
    contentDescription: String?,
    modifier: Modifier = Modifier
    //tint: Color = LocalContentColor.current
)
