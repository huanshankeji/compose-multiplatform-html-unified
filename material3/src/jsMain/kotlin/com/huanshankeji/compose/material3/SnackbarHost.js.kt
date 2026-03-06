package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MaterialWebAdditionsApi
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSnackbar
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.dom.Text

// copied and adapted from `SnackbarHost.js.kt` in material2 and from `SnackbarHost.kt` in `androidx.compose.material3`

@Stable
actual class SnackbarHostState internal constructor(internal val platformValue: SnackbarHostStateCommonImpl) {
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
        withDismissAction: Boolean,
        duration: SnackbarDuration
    ): SnackbarResult =
        platformValue.showSnackbar(message, actionLabel, withDismissAction, duration)

    actual suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult =
        platformValue.showSnackbar(visuals.toCommonInterface())
}

internal fun SnackbarDataCommonInterface.toCommonValue(): SnackbarData =
    object : SnackbarData {
        val commonInterface = this@toCommonValue
        override val visuals: SnackbarVisuals get() = commonInterface.visuals
        override fun performAction() = commonInterface.performAction()
        override fun dismiss() = commonInterface.dismiss()
    }

internal fun SnackbarVisuals.toCommonInterface(): SnackbarVisualsCommonInterface {
    val visuals = this
    return object : SnackbarVisualsCommonInterface {
        override val message get() = visuals.message
        override val actionLabel get() = visuals.actionLabel
        override val withDismissAction get() = visuals.withDismissAction
        override val duration get() = visuals.duration
    }
}

@OptIn(MaterialWebAdditionsApi::class)
@Composable
actual fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier
) {
    val currentSnackbarData = hostState.currentSnackbarData

    val duration: Long?
    if (currentSnackbarData != null) {
        duration = currentSnackbarData.visuals.duration.toMillis()

        /**
         * See commit a1dfeaea82384b6e33b3807482ed01ba4311075f in material2 for some alternative approaches
         * to solve the issue that a series of continuous snackbars don't work on JS without [SnackbarHostStateCommonImpl.delayMillisUntilNext],
         * which all failed.
         */
        /**
         * It seems possible that the [SnackbarHostStateCommonImpl.delayMillisUntilNext] in [SnackbarHostState] is not enough to trigger recomposition,
         * the snackbar is updated with the next [SnackbarData],
         * the [MdSnackbar] onClosed event is never fired,
         * and [SnackbarHostStateCommonImpl.mutex] never gets unlocked.
         * This [LaunchedEffect] makes sure it's unlocked, though some queuing snackbars may not show when this happens.
         */
        LaunchedEffect(currentSnackbarData) {
            delay(duration)
            currentSnackbarData.dismiss()
        }
    } else
        duration = null

    // not put in a conditional block to reduce DOM recomposition
    MdSnackbar(
        open = (currentSnackbarData != null).isTrueOrNull(),
        actionText = currentSnackbarData?.visuals?.actionLabel,
        timeout = duration?.let { if (it == Long.MAX_VALUE) null else it.toInt() },
        onAction = currentSnackbarData?.let { { it.performAction() } },
        onClosed = currentSnackbarData?.let { { it.dismiss() } },
        attrs = modifier.toAttrs()
    ) {
        Text(currentSnackbarData?.visuals?.message ?: "") // to reduce DOM recomposition in `SnackbarHost`
    }
}

internal fun SnackbarDuration.toMillis(): Long =
    when (this) {
        SnackbarDuration.Indefinite -> Long.MAX_VALUE
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Short -> 4000L
    }
