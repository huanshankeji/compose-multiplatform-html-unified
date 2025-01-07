package com.huanshankeji.compose

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

fun (@Composable (Modifier) -> Unit).toContentWithoutModifier(): @Composable () -> Unit =
    { this(Modifier) }

fun (@Composable (Modifier) -> Unit)?.toNullableContentWithoutModifier(): @Composable (() -> Unit)? =
    this?.toContentWithoutModifier()
