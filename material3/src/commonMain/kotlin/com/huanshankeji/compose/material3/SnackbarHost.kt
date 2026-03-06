package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier

// copied and adapted from `SnackbarHost.kt` in `androidx.compose.material3`

@Stable
interface SnackbarVisuals {
    val message: String
    val actionLabel: String?
    val withDismissAction: Boolean
    val duration: SnackbarDuration
}

@Stable
interface SnackbarData {
    val visuals: SnackbarVisuals
    fun performAction()
    fun dismiss()
}

enum class SnackbarResult {
    Dismissed, ActionPerformed
}

enum class SnackbarDuration {
    Short, Long, Indefinite
}

@Stable
expect class SnackbarHostState() {
    val currentSnackbarData: SnackbarData?

    suspend fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite
    ): SnackbarResult

    suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult
}

/**
 * Material Design snackbar host.
 *
 * @see <a href="https://m3.material.io/components/snackbar/overview">Material Design snackbar</a>
 * @see androidx.compose.material3.SnackbarHost
 */
@Composable
expect fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier
)
