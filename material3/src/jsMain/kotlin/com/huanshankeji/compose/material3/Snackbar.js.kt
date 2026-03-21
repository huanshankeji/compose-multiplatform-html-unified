package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material3.ext.CommonSnackbar
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier,
    actionOnNewLine: Boolean,
) =
    CommonSnackbar(true, snackbarData, modifier, actionOnNewLine)
