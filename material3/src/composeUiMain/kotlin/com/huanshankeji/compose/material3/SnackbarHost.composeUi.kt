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
        platformValue.showSnackbar(visuals.toPlatformValue()).toCommonValue()
}

private class PlatformSnackbarDataWrapper(val platformValue: PlatformSnackbarData) : SnackbarData {
    override val visuals: SnackbarVisuals
        get() = platformValue.visuals.toCommonValue()

    override fun performAction() = platformValue.performAction()
    override fun dismiss() = platformValue.dismiss()
}

fun PlatformSnackbarData.toCommonValue(): SnackbarData =
    PlatformSnackbarDataWrapper(this)

private class PlatformSnackbarVisualsWrapper(val platformValue: PlatformSnackbarVisuals) : SnackbarVisuals {
    override val message: String get() = platformValue.message
    override val actionLabel: String? get() = platformValue.actionLabel
    override val withDismissAction: Boolean get() = platformValue.withDismissAction
    override val duration: SnackbarDuration get() = platformValue.duration.toCommonValue()
}

fun PlatformSnackbarVisuals.toCommonValue(): SnackbarVisuals =
    PlatformSnackbarVisualsWrapper(this)

private class CommonSnackbarVisualsWrapper(val commonValue: SnackbarVisuals) : PlatformSnackbarVisuals {
    override val message: String get() = commonValue.message
    override val actionLabel: String? get() = commonValue.actionLabel
    override val withDismissAction: Boolean get() = commonValue.withDismissAction
    override val duration: PlatformSnackbarDuration get() = commonValue.duration.toPlatformValue()
}

fun SnackbarVisuals.toPlatformValue(): PlatformSnackbarVisuals =
    CommonSnackbarVisualsWrapper(this)

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
) =
    androidx.compose.material3.SnackbarHost(hostState.platformValue, modifier.platformModifier)
