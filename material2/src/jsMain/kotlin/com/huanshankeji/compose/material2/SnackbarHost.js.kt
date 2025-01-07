package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
import dev.petuska.kmdc.snackbar.MDCSnackbarAttrsScope
import kotlinx.coroutines.delay

// copied and adapted from `SnackbarHost.kt` in `androidx.compose.material`

@Stable
actual class SnackbarHostState(val platformValue: SnackbarHostStateCommonImpl) {
    actual constructor() : this(object : SnackbarHostStateCommonImpl() {
        override val yieldUntilNext: Boolean
            get() = false
        override val delayMillisUntilNext: Long
            get() = 20 // for 60 Hz displays
    })

    actual val currentSnackbarData: SnackbarData?
        get() = platformValue.currentSnackbarData?.toCommonValue()

    actual suspend fun showSnackbar(
        message: String,
        actionLabel: String?,
        duration: SnackbarDuration
    ): SnackbarResult =
        platformValue.showSnackbar(message, actionLabel, duration)
}

@Stable
actual class SnackbarData(val platformValue: SnackbarDataCommonInterface) {
    actual val message: String
        get() = platformValue.message
    actual val actionLabel: String?
        get() = platformValue.actionLabel
    actual val duration: SnackbarDuration
        get() = platformValue.duration

    actual fun performAction() = platformValue.performAction()

    actual fun dismiss() = platformValue.dismiss()
}

fun SnackbarDataCommonInterface.toCommonValue() =
    SnackbarData(this)

@Composable
actual fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier,
    actionOnNewLine: Boolean
) {
    val currentSnackbarData = hostState.currentSnackbarData?.platformValue

    val duration: Long?
    if (currentSnackbarData !== null) {
        duration = currentSnackbarData.duration.toMillis()

        /**
         * See commit a1dfeaea82384b6e33b3807482ed01ba4311075f for some alternative approaches
         * to solve the issue that a series of continuous snackbars don't work on JS without [SnackbarHostStateCommonImpl.delayMillisUntilNext],
         * which all failed.
         */
        /**
         * It seems possible that the [SnackbarHostStateCommonImpl.delayMillisUntilNext] in [SnackbarHostState] is not enough to trigger recomposition,
         * the snackbar is updated with the next [SnackbarData],
         * the [MDCSnackbarAttrsScope.onClosed] in [CommonSnackbar] is never called,
         * and [SnackbarHostStateCommonImpl.mutex] never gets unlocked.
         * This [LaunchedEffect] makes sure it's unlocked, though some queuing snackbars may not show when this happens.
         */
        // also listen for the close event.
        LaunchedEffect(currentSnackbarData) {
            delay(duration)
            currentSnackbarData.dismiss()
        }
    } else
        duration = null

    // not put in a conditional block to reduce DOM recomposition
    CommonSnackbar(actionOnNewLine, currentSnackbarData !== null, duration?.toInt(), modifier, currentSnackbarData)
}

internal fun SnackbarDuration.toMillis(
    //hasAction: Boolean,
    //accessibilityManager: AccessibilityManager?
): Long =
    when (this) {
        SnackbarDuration.Indefinite -> Long.MAX_VALUE
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Short -> 4000L
    }
