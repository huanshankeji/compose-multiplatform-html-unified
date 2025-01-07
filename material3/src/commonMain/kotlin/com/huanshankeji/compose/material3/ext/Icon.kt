package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material3.Icon
import com.huanshankeji.compose.ui.Modifier

fun Icon.toContentWithModifier(): @Composable (Modifier) -> Unit =
    { modifier -> Icon(this, null, modifier) }

fun Icon?.toNullableContentWithModifier(): @Composable ((Modifier) -> Unit)? =
    this?.toContentWithModifier()
