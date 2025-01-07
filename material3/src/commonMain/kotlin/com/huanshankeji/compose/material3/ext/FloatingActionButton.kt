package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
)

@Composable
fun FloatingActionButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Icon
) =
    FloatingActionButton(onClick, modifier, icon.toContentWithModifier())

@Composable
expect fun SmallFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
)

@Composable
fun SmallFloatingActionButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Icon
) =
    SmallFloatingActionButton(onClick, modifier, icon.toContentWithModifier())

@Composable
expect fun LargeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
)

@Composable
fun LargeFloatingActionButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Icon
) =
    LargeFloatingActionButton(onClick, modifier, icon.toContentWithModifier())

@Composable
expect fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    content: @Composable ((Modifier) -> Unit)?
)

@Composable
fun ExtendedFloatingActionButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    icon: Icon?
) =
    ExtendedFloatingActionButton(onClick, modifier, label, icon.toNullableContentWithModifier())
