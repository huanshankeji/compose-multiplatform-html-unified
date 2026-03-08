package com.huanshankeji.compose.material3

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.yield
import kotlin.coroutines.resume

// copied and adapted from `SnackbarHostStateCommonImpl.kt` in `com.huanshankeji.compose.material2`

@Stable
abstract class SnackbarHostStateCommonImpl {
    private val mutex = Mutex()
    var currentSnackbarData by mutableStateOf<SnackbarData?>(null)
        private set

    abstract val yieldUntilNext: Boolean
    abstract val delayMillisUntilNext: Long

    suspend fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    ): SnackbarResult =
        showSnackbar(SnackbarVisualsImpl(message, actionLabel, withDismissAction, duration))

    suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult = mutex.withLock {
        try {
            return suspendCancellableCoroutine { continuation ->
                currentSnackbarData = SnackbarDataImpl(visuals, continuation)
            }
        } finally {
            currentSnackbarData = null

            // a workaround to trigger recomposition when `currentSnackbarData` is set to `null`, resolving the issue that a series of continuous snackbars don't show
            if (yieldUntilNext) yield()
            delay(delayMillisUntilNext)
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
