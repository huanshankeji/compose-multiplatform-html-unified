package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/styles/icons/overview
https://developer.android.com/develop/ui/compose/graphics/images/material
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-icon.html
 */

@Composable
expect fun Icon(
    icon: Icon,
    contentDescription: String?,
    modifier: Modifier = Modifier
    //tint: Color = LocalContentColor.current
)
