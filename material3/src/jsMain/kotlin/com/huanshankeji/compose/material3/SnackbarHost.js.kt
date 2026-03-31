package com.huanshankeji.compose.material3

import androidx.compose.runtime.*
import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.ui.Modifier
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.resume

// copied and adapted from `SnackbarHost.js.kt` in `com.huanshankeji.compose.material2`

// copied and adapted from `SnackbarHostStateCommonImpl.kt` in `com.huanshankeji.compose.material2` following `SnackbarHost.kt` in `androidx.compose.material3`
@Stable
actual class SnackbarHostState {
    private val mutex = Mutex()

    /*
    var currentSnackbarData by mutableStateOf<SnackbarData?>(null)
        private set
    */
    private var _currentSnackbarData by mutableStateOf<SnackbarData?>(null)
        private set
    actual val currentSnackbarData get() = _currentSnackbarData

    actual suspend fun showSnackbar(
        message: String,
        actionLabel: String?,
        withDismissAction: Boolean,
        duration: SnackbarDuration,
    ): SnackbarResult =
        showSnackbar(SnackbarVisualsImpl(message, actionLabel, withDismissAction, duration))

    actual suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult = mutex.withLock {
        try {
            return suspendCancellableCoroutine { continuation ->
                _currentSnackbarData = SnackbarDataImpl(visuals, continuation)
            }
        } finally {
            _currentSnackbarData = null
        }
    }

    @Stable
    private class SnackbarVisualsImpl(
        override val message: String,
        override val actionLabel: String?,
        override val withDismissAction: Boolean,
        override val duration: SnackbarDuration,
    ) : SnackbarVisuals

    @Stable
    private class SnackbarDataImpl(
        override val visuals: SnackbarVisuals,
        private val continuation: CancellableContinuation<SnackbarResult>,
    ) : SnackbarData {

        override fun performAction() {
            if (continuation.isActive) continuation.resume(SnackbarResult.ActionPerformed)
        }

        override fun dismiss() {
            if (continuation.isActive) continuation.resume(SnackbarResult.Dismissed)
        }
    }
}

@Stable
actual interface SnackbarVisuals {
    actual val message: String
    actual val actionLabel: String?
    actual val withDismissAction: Boolean
    actual val duration: SnackbarDuration
}

@Stable
actual interface SnackbarData {
    actual val visuals: SnackbarVisuals
    actual fun performAction()
    actual fun dismiss()
}

@Composable
actual fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier,
    snackbar: @Composable (SnackbarData) -> Unit,
) {
    // Copied and adapted from `SnackbarHost` in `androidx.compose.material3`. Do not edit without referencing to the original corresponding implementation.
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
    Box(modifier) {
        if (currentSnackbarData != null) {
            // The `key` call is necessary here. Otherwise, the snackbar appearances after the first one don't show.
            key(currentSnackbarData) {
                snackbar(currentSnackbarData)
            }
        }
    }
}

actual enum class SnackbarResult {
    Dismissed, ActionPerformed
}

actual enum class SnackbarDuration {
    Short, Long, Indefinite
}

// simplified compared to the corresponding function in `androidx.compose.material3`
internal fun SnackbarDuration.toMillis(): Long =
    when (this) {
        /*
        `Long.MAX_VALUE` from Compose UI causes a bug that the snackbar doesn't show here.

        Other values tried that don't work:
        - `JsNumber.MAX_VALUE.toLong()`
        - `JsNumber.MAX_VALUE.toLong() - 1`
        - `Int.MAX_VALUE.toLong() + 1`

        This is confirmed by that it uses JS `setTimeout` underneath (https://github.com/maicol07/material-web-additions/blob/096590484ce31dfb18d8e2d1998989ed933328e1/snackbar/internal/snackbar.ts#L102-L105) and the max value allowed is `Int.MAX_VALUE`.
         */
        SnackbarDuration.Indefinite -> Int.MAX_VALUE.toLong()
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Short -> 4000L
    }
