package com.huanshankeji.compose.material3

/*
// copied and adapted from `SnackbarHost.kt` in `androidx.compose.material3`

@Stable
expect class SnackbarHostState {
    var currentSnackbarData by mutableStateOf<SnackbarData?>(null)
        private set

    suspend fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration =
            if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite
    ): SnackbarResult

    suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult
}

@Composable
expect fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
)

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
*/
