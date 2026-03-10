package com.huanshankeji.compose.material3

import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier,
    actionOnNewLine: Boolean,
) =
    Snackbar(
        snackbarData.platformValue,
        modifier.platformModifier,
        actionOnNewLine,
    )
