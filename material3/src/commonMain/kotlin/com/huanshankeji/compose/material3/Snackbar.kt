package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/snackbar/overview
https://developer.android.com/develop/ui/compose/components/snackbar
 */

/**
 * @param actionOnNewLine only effective on Compose UI now.
 */
@Composable
expect fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
)
