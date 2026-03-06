package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material3.Snackbar as PlatformSnackbar

@Composable
actual fun Snackbar(
    modifier: Modifier,
    action: @Composable (() -> Unit)?,
    dismissAction: @Composable (() -> Unit)?,
    actionOnNewLine: Boolean,
    content: @Composable () -> Unit
) =
    PlatformSnackbar(modifier.platformModifier, action, dismissAction, actionOnNewLine, content = content)

@Composable
actual fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier,
    actionOnNewLine: Boolean,
    withDismissAction: Boolean
) {
    val visuals = snackbarData.visuals
    PlatformSnackbar(
        object : androidx.compose.material3.SnackbarData {
            override val visuals = object : androidx.compose.material3.SnackbarVisuals {
                override val message = visuals.message
                override val actionLabel = visuals.actionLabel
                override val withDismissAction = withDismissAction
                override val duration = visuals.duration.toPlatformValue()
            }

            override fun performAction() = snackbarData.performAction()
            override fun dismiss() = snackbarData.dismiss()
        },
        modifier.platformModifier,
        actionOnNewLine
    )
}

