package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/switch/overview
https://developer.android.com/develop/ui/compose/components/switch
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-switch.html
 */

@Composable
expect fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
)
