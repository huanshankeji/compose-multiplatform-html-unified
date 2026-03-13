package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.material3.SnackbarData
import com.huanshankeji.compose.ui.Modifier

@ExperimentalApi
sealed class OptionalSnackbarArgs {
    object Closed : OptionalSnackbarArgs()

    /**
     * @param actionOnNewLine only effective on Compose UI now.
     */
    class Open(
        val snackbarData: SnackbarData,
        val modifier: Modifier = Modifier,
        val actionOnNewLine: Boolean = false,
    ) : OptionalSnackbarArgs()
}

/**
 * A variant of [com.huanshankeji.compose.material3.Snackbar] with its open state delegating to its more idiomatic `open` attribute on JS DOM,
 * providing an alternative implementation to use in [com.huanshankeji.compose.material3.SnackbarHost] on JS DOM, though not used now.
 */
@ExperimentalApi
@Composable
expect fun Snackbar(
    optionalSnackbarArgs: OptionalSnackbarArgs
)
