package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
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

fun PlatformSnackbarHostState.toCommonValue() = SnackbarHostState(this)

fun androidx.compose.material3.SnackbarData.toCommonValue(): SnackbarData {
    val platformData = this
    return object : SnackbarData {
        override val visuals = object : SnackbarVisuals {
            override val message = platformData.visuals.message
            override val actionLabel = platformData.visuals.actionLabel
            override val withDismissAction = platformData.visuals.withDismissAction
            override val duration = platformData.visuals.duration.toCommonValue()
        }

        override fun performAction() = platformData.performAction()
        override fun dismiss() = platformData.dismiss()
    }
}

fun SnackbarVisuals.toPlatformValue(): PlatformSnackbarVisuals {
    val commonVisuals = this
    return object : PlatformSnackbarVisuals {
        override val message = commonVisuals.message
        override val actionLabel = commonVisuals.actionLabel
        override val withDismissAction = commonVisuals.withDismissAction
        override val duration = commonVisuals.duration.toPlatformValue()
    }
}

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
    modifier: Modifier
) =
    androidx.compose.material3.SnackbarHost(hostState.platformValue, modifier.platformModifier)
