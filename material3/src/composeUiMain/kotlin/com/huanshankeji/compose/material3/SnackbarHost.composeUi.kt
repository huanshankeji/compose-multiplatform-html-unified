package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material3.SnackbarData as PlatformSnackbarData
import androidx.compose.material3.SnackbarDuration as PlatformSnackbarDuration
import androidx.compose.material3.SnackbarHostState as PlatformSnackbarHostState
import androidx.compose.material3.SnackbarResult as PlatformSnackbarResult
import androidx.compose.material3.SnackbarVisuals as PlatformSnackbarVisuals

/*
Can't refactor this to use typealias:

```
Default argument values inside expect declaration 'SnackbarHostState' are not allowed if it is actualized via typealias. Possible fix is to remove default argument values in members:
expect suspend fun showSnackbar(message: String, actionLabel: String? = ..., withDismissAction: Boolean = ..., duration: SnackbarDuration = ...): SnackbarResult
```
 */
//actual typealias SnackbarHostState = PlatformSnackbarHostState

@Stable
actual class SnackbarHostState(val platformValue: PlatformSnackbarHostState) {
    actual constructor() : this(PlatformSnackbarHostState())

    actual val currentSnackbarData: SnackbarData?
        get() = platformValue.currentSnackbarData

    actual suspend fun showSnackbar(
        message: String,
        actionLabel: String?,
        withDismissAction: Boolean,
        duration: SnackbarDuration
    ): SnackbarResult =
        platformValue.showSnackbar(message, actionLabel, withDismissAction, duration)

    actual suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult =
        platformValue.showSnackbar(visuals)
}

actual typealias SnackbarVisuals = PlatformSnackbarVisuals

actual typealias SnackbarData = PlatformSnackbarData

actual typealias SnackbarResult = PlatformSnackbarResult

actual typealias SnackbarDuration = PlatformSnackbarDuration

@Composable
actual fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier,
    snackbar: @Composable (SnackbarData) -> Unit,
) =
    androidx.compose.material3.SnackbarHost(
        hostState.platformValue,
        modifier.platformModifier,
        snackbar,
    )
