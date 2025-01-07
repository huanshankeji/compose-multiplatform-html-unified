package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

// copied and adapted from `Snackbar.kt` in `androidx.compose.material`

@Composable
expect fun Snackbar(
    modifier: Modifier = Modifier,
    action: @Composable (() -> Unit)? = null,
    actionOnNewLine: Boolean = false,
    /*
    shape: Shape = MaterialTheme.shapes.small,
    backgroundColor: Color = SnackbarDefaults.backgroundColor,
    contentColor: Color = MaterialTheme.colors.surface,
    elevation: Dp = 6.dp,
    */
    content: @Composable () -> Unit
)

// consider removing `expect` and copying the code from `Snackbar.kt` directly
@Composable
expect fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    /*
    shape: Shape = MaterialTheme.shapes.small,
    backgroundColor: Color = SnackbarDefaults.backgroundColor,
    contentColor: Color = MaterialTheme.colors.surface,
    actionColor: Color = SnackbarDefaults.primaryActionColor,
    elevation: Dp = 6.dp
    */
)
