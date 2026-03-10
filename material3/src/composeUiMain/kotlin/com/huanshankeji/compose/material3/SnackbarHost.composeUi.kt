package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material3.SnackbarData as PlatformSnackbarData
import androidx.compose.material3.SnackbarDuration as PlatformSnackbarDuration
import androidx.compose.material3.SnackbarHostState as PlatformSnackbarHostState
import androidx.compose.material3.SnackbarResult as PlatformSnackbarResult
import androidx.compose.material3.SnackbarVisuals as PlatformSnackbarVisuals

@Stable
actual class SnackbarHostState(val platformValue: PlatformSnackbarHostState) {
    actual constructor() : this(PlatformSnackbarHostState())

    actual val currentSnackbarData: SnackbarData?
        get() = platformValue.currentSnackbarData?.toCommonValue()

    actual suspend fun showSnackbar(
        message: String,
        actionLabel: String?,
        withDismissAction: Boolean,
        duration: SnackbarDuration
    ): SnackbarResult =
        platformValue.showSnackbar(message, actionLabel, withDismissAction, duration.toPlatformValue())
            .toCommonValue()

    actual suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult =
        platformValue.showSnackbar(visuals.platformValue).toCommonValue()
}

@Stable
actual class SnackbarVisuals(val platformValue: PlatformSnackbarVisuals) {
    actual constructor(
        message: String,
        actionLabel: String?,
        withDismissActionComposeUi: Boolean,
        duration: SnackbarDuration,
    ) : this(object : PlatformSnackbarVisuals {
        override val message: String = message
        override val actionLabel: String? = actionLabel
        override val duration: PlatformSnackbarDuration = duration.toPlatformValue()
        override val withDismissAction: Boolean = withDismissActionComposeUi
    }) // can't just use `SnackbarVisualsImpl` here because it's private

    actual val message: String = platformValue.message
    actual val actionLabel: String? = platformValue.actionLabel
    actual val withDismissActionComposeUi: Boolean = platformValue.withDismissAction
    actual val duration: SnackbarDuration = platformValue.duration.toCommonValue()
}

fun PlatformSnackbarVisuals.toCommonValue(): SnackbarVisuals =
    SnackbarVisuals(this)

@Stable
actual class SnackbarData(val platformValue: PlatformSnackbarData) {
    actual val visuals: SnackbarVisuals
        get() = platformValue.visuals.toCommonValue()

    actual fun performAction() = platformValue.performAction()
    actual fun dismiss() = platformValue.dismiss()
}

fun PlatformSnackbarData.toCommonValue(): SnackbarData =
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
    snackbar: @Composable (SnackbarData) -> Unit,
) =
    androidx.compose.material3.SnackbarHost(
        hostState.platformValue,
        modifier.platformModifier,
        snackbar = { snackbar(it.toCommonValue()) },
    )
