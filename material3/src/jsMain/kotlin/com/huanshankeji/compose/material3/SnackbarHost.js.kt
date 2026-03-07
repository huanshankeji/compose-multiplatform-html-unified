package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSnackbar
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.Text

// copied and adapted from `SnackbarHost.js.kt` in `com.huanshankeji.compose.material2`

@Stable
actual class SnackbarHostState(val commonImpl: SnackbarHostStateCommonImpl) {
    actual constructor() : this(object : SnackbarHostStateCommonImpl() {
        override val yieldUntilNext: Boolean
            get() = false
        override val delayMillisUntilNext: Long
            get() = 20 // for 60 Hz displays
    })

    actual val currentSnackbarData: SnackbarData?
        get() = commonImpl.currentSnackbarData

    actual suspend fun showSnackbar(
        message: String,
        actionLabel: String?,
        withDismissAction: Boolean,
        duration: SnackbarDuration
    ): SnackbarResult =
        commonImpl.showSnackbar(message, actionLabel, withDismissAction, duration)

    actual suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult =
        commonImpl.showSnackbar(visuals)
}

@Composable
actual fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier,
) {
    val currentSnackbarData = hostState.currentSnackbarData

    if (currentSnackbarData != null) {
        val durationMillis = currentSnackbarData.visuals.duration.toMillis()

        // Make sure the snackbar is dismissed after its duration, similar to the M2 approach.
        LaunchedEffect(currentSnackbarData) {
            delay(durationMillis)
            currentSnackbarData.dismiss()
        }
    }

    // not put in a conditional block to reduce DOM recomposition
    // Don't use `onClosed`: the web component fires this event asynchronously after its close animation.
    // When snackbars are shown in rapid succession, the `onClosed` from a previous snackbar fires
    // after recomposition with new data, causing the callback to dismiss the next snackbar immediately.
    // This creates a cascade where subsequent snackbars flash instead of showing for their full duration.
    // Timing is managed solely by `LaunchedEffect` above to match Compose UI behavior.
    MdSnackbar(
        open = if (currentSnackbarData != null) true else null,
        actionText = currentSnackbarData?.visuals?.actionLabel,
        onAction = currentSnackbarData?.let { data -> { data.performAction() } },
        attrs = modifier.toAttrs()
    ) {
        Text(currentSnackbarData?.visuals?.message ?: "")
    }
}

internal fun SnackbarDuration.toMillis(): Long =
    when (this) {
        SnackbarDuration.Indefinite -> Long.MAX_VALUE
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Short -> 4000L
    }
