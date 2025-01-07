package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material.Snackbar as PlatformSnackbar

@Composable
actual fun Snackbar(
    modifier: Modifier,
    action: @Composable (() -> Unit)?,
    actionOnNewLine: Boolean,
    content: @Composable () -> Unit
) =
    PlatformSnackbar(modifier.platformModifier, action, actionOnNewLine, content = content)

@Composable
actual fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier,
    actionOnNewLine: Boolean
) =
    PlatformSnackbar(snackbarData.platformValue, modifier.platformModifier, actionOnNewLine)
