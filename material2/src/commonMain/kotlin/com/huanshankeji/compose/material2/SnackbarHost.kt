package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier

// Common copies of these 2 types are not used because converting them to and from create additional wrappers and may be expensive.

@Stable
expect class SnackbarHostState() {
    val currentSnackbarData: SnackbarData?

    suspend fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ): SnackbarResult
}

@Stable
expect class SnackbarData {
    val message: String
    val actionLabel: String?
    val duration: SnackbarDuration
    fun performAction()
    fun dismiss()
}

@Composable
expect fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    //snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
    actionOnNewLine: Boolean = false // an additional parameter added because passing a custom `Snackbar` comopsable on JS is not supported
)
