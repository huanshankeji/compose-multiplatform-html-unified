package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.material3.Snackbar

@ExperimentalApi
@Composable
actual fun Snackbar(optionalSnackbarArgs: OptionalSnackbarArgs) =
    when (optionalSnackbarArgs) {
        OptionalSnackbarArgs.Closed -> Unit
        is OptionalSnackbarArgs.Open -> with(optionalSnackbarArgs) {
            Snackbar(snackbarData, modifier, actionOnNewLine)
        }
    }
