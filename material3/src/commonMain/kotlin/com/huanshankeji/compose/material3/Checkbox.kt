package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/checkbox/overview
https://developer.android.com/develop/ui/compose/components/checkbox
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-checkbox.html
 */

@Composable
expect fun Checkbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
)
