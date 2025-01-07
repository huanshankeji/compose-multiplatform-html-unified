package com.huanshankeji.compose.material3.ext

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.toContentWithoutModifier
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable (Modifier) -> Unit
) =
    com.huanshankeji.compose.material3.FloatingActionButton(onClick, modifier, content.toContentWithoutModifier())

@Composable
actual fun SmallFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable (Modifier) -> Unit
) =
    com.huanshankeji.compose.material3.SmallFloatingActionButton(onClick, modifier, content.toContentWithoutModifier())

@Composable
actual fun LargeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable (Modifier) -> Unit
) =
    com.huanshankeji.compose.material3.LargeFloatingActionButton(onClick, modifier, content.toContentWithoutModifier())

@Composable
actual fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    label: String,
    content: @Composable ((Modifier) -> Unit)?
) =
    androidx.compose.material3.ExtendedFloatingActionButton(onClick, modifier.platformModifier) {
        content?.invoke(Modifier)
        Text(label)
    }
