package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier

/*
https://developer.android.com/develop/ui/compose/components/snackbar
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-snackbar.html
 */

// copied and adapted from `SnackbarHost.kt` in `androidx.compose.material3`

// Material 3 `androidx.compose.material3.SnackbarHostState` does differ from Material 2 `androidx.compose.material.SnackbarHostState` in some places.
@Stable
expect class SnackbarHostState() {
    val currentSnackbarData: SnackbarData?

    suspend fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    ): SnackbarResult

    suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult
}

@Composable
expect fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) },
)

// TODO update this comment with the new `expect interface` design
/*
These types whose corresponding types are interfaces in `SnackbarHost.kt` in `androidx.compose.material3` are made expect classes because users should not be allowed to implement them.
They should always wrap a platform type, so the wrapping wouldn't stack up when converting to and from platform types.
Therefore, their factory functions should also always delegate to platform implementations.
 */

/*
This is only available in Material 3 `androidx.compose.material3`.
It wraps the parameters of `SnackbarHostState.showSnackbar` with an additional `withDismissAction` parameter compared to Material 2.
 */
@Stable
expect class SnackbarVisuals(
    message: String,
    actionLabel: String?,
    // TODO This could be supported on JS by showing an extra button.
    withDismissActionComposeUi: Boolean,
    duration: SnackbarDuration,
) {
    val message: String
    val actionLabel: String?
    val withDismissActionComposeUi: Boolean
    val duration: SnackbarDuration
}

@Stable
expect class SnackbarData {
    val visuals: SnackbarVisuals
    fun performAction()
    fun dismiss()
}

// TODO `expect enum class` works now. Adopt this design to simplify and improve performance.
/*expect*/ enum class SnackbarResult {
    Dismissed, ActionPerformed
}

enum class SnackbarDuration {
    Short, Long, Indefinite
}
