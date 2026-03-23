package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/floating-action-button/overview
https://developer.android.com/develop/ui/compose/components/fab
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-floating-action-button.html
 */

@Composable
expect fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
)

@Composable
fun FloatingActionButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Icon,
) =
    FloatingActionButton(onClick, modifier, icon.toContentWithModifier())

@Composable
expect fun SmallFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
)

@Composable
fun SmallFloatingActionButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Icon,
) =
    SmallFloatingActionButton(onClick, modifier, icon.toContentWithModifier())

@Composable
expect fun LargeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
)

@Composable
fun LargeFloatingActionButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Icon,
) =
    LargeFloatingActionButton(onClick, modifier, icon.toContentWithModifier())

@Composable
expect fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    content: @Composable ((Modifier) -> Unit)?,
)

@Composable
fun ExtendedFloatingActionButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    icon: Icon?,
) =
    ExtendedFloatingActionButton(onClick, modifier, label, icon.toNullableContentWithModifier())
