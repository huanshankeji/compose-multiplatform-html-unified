package com.huanshankeji.compose.material2

import androidx.compose.material.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material.SnackbarData as PlatformSnackbarData
import androidx.compose.material.SnackbarDuration as PlatformSnackbarDuration
import androidx.compose.material.SnackbarHostState as PlatformSnackbarHostState
import androidx.compose.material.SnackbarResult as PlatformSnackbarResult

@Stable
actual class SnackbarHostState(val platformValue: PlatformSnackbarHostState) {
    actual constructor() : this(PlatformSnackbarHostState())

    actual val currentSnackbarData: SnackbarData?
        get() = platformValue.currentSnackbarData?.toCommonValue()

    actual suspend fun showSnackbar(
        message: String,
        actionLabel: String?,
        duration: SnackbarDuration
    ): SnackbarResult =
        platformValue.showSnackbar(message, actionLabel, duration.toPlatformValue()).toCommonValue()
}

fun PlatformSnackbarHostState.toCommonValue() =
    SnackbarHostState(this)

@Stable
actual class SnackbarData(val platformValue: PlatformSnackbarData) {
    actual val message: String
        get() = platformValue.message
    actual val actionLabel: String?
        get() = platformValue.actionLabel
    actual val duration: SnackbarDuration
        get() = platformValue.duration.toCommonValue()

    actual fun performAction() = platformValue.performAction()

    actual fun dismiss() = platformValue.dismiss()
}

fun PlatformSnackbarData.toCommonValue() =
    SnackbarData(this)

fun PlatformSnackbarResult.toCommonValue() =
    when (this) {
        PlatformSnackbarResult.Dismissed -> SnackbarResult.Dismissed
        PlatformSnackbarResult.ActionPerformed -> SnackbarResult.ActionPerformed
    }

fun SnackbarDuration.toPlatformValue() =
    when (this) {
        SnackbarDuration.Short -> PlatformSnackbarDuration.Short
        SnackbarDuration.Long -> PlatformSnackbarDuration.Long
        SnackbarDuration.Indefinite -> PlatformSnackbarDuration.Indefinite
    }

fun PlatformSnackbarDuration.toCommonValue() =
    when (this) {
        PlatformSnackbarDuration.Short -> SnackbarDuration.Short
        PlatformSnackbarDuration.Long -> SnackbarDuration.Long
        PlatformSnackbarDuration.Indefinite -> SnackbarDuration.Indefinite
    }

@Composable
actual fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier,
    actionOnNewLine: Boolean
) =
    androidx.compose.material.SnackbarHost(hostState.platformValue, modifier.platformModifier) {
        Snackbar(it, actionOnNewLine = actionOnNewLine)
    }
