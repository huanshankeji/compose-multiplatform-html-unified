package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.key
import com.huanshankeji.compose.ui.Modifier
import kotlinx.coroutines.delay

// copied and adapted from `SnackbarHost.js.kt` in `com.huanshankeji.compose.material2`

@Stable
actual class SnackbarHostState(val commonImpl: SnackbarHostStateCommonImpl) {
    actual constructor() : this(object : SnackbarHostStateCommonImpl() {
        // TODO remove them if they are confirmed to be not needed
        /*
        override val yieldUntilNext: Boolean
            get() = false
        override val delayMillisUntilNext: Long
            get() = 20 // for 60 Hz displays
        */
    })

    actual val currentSnackbarData: SnackbarData?
        get() = commonImpl.currentSnackbarData

    actual suspend fun showSnackbar(
        message: String,
        actionLabel: String?,
        withDismissAction: Boolean,
        duration: SnackbarDuration,
    ): SnackbarResult =
        commonImpl.showSnackbar(message, actionLabel, withDismissAction, duration)

    actual suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult =
        commonImpl.showSnackbar(visuals)
}

@Stable
actual class SnackbarVisuals actual constructor(
    actual val message: String,
    actual val actionLabel: String?,
    actual val withDismissActionComposeUi: Boolean,
    actual val duration: SnackbarDuration,
)

// TODO incomplete
@Stable
actual class SnackbarData(
    actual val visuals: SnackbarVisuals,
) {
    actual fun performAction() {
        TODO()
    }

    actual fun dismiss() {
        TODO()
    }
}

@Composable
actual fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier,
    snackbar: @Composable (SnackbarData) -> Unit,
) {
    val currentSnackbarData = hostState.currentSnackbarData
    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            val duration = currentSnackbarData.visuals.duration.toMillis()
            delay(duration)
            currentSnackbarData.dismiss()
        }
    }

    // Use `key` to create a fresh snackbar element for each snackbar invocation.
    // Without this, the web component's internal close animation state can prevent
    // a new snackbar from appearing when the previous one was just dismissed,
    // causing every other snackbar to be skipped in rapid succession.
    // TODO Not sure whether this breaks the correct behavior. Not verified yet because the code has compilation errors now.
    if (currentSnackbarData != null)
        key(currentSnackbarData) {
            snackbar(currentSnackbarData)
        }
}

// simplified compared to the corresponding function in `androidx.compose.material3`
internal fun SnackbarDuration.toMillis(): Long =
    when (this) {
        // TODO Seems 0 or `null` can be used for JS.
        SnackbarDuration.Indefinite -> Long.MAX_VALUE
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Short -> 4000L
    }
