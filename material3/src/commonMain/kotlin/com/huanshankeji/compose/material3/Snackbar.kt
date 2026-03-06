package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

// copied and adapted from `Snackbar.kt` in `androidx.compose.material3`

/**
 * Material Design snackbar.
 *
 * Note: [dismissAction] is currently not supported on JS DOM.
 *
 * @see <a href="https://m3.material.io/components/snackbar/overview">Material Design snackbar</a>
 * @see androidx.compose.material3.Snackbar
 */
@Composable
expect fun Snackbar(
    modifier: Modifier = Modifier,
    action: @Composable (() -> Unit)? = null,
    dismissAction: @Composable (() -> Unit)? = null,
    actionOnNewLine: Boolean = false,
    content: @Composable () -> Unit
)

@Composable
expect fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    withDismissAction: Boolean = snackbarData.visuals.withDismissAction
)
