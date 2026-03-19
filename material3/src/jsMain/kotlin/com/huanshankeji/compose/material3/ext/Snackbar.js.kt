package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSnackbar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSnackbarScope
import com.huanshankeji.compose.material.icons.Icons
import com.huanshankeji.compose.material.icons.filled.Close
import com.huanshankeji.compose.material3.Icon
import com.huanshankeji.compose.material3.SnackbarData
import com.huanshankeji.compose.material3.toMillis
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Text

@ExperimentalApi
@Composable
internal fun CommonSnackbar(
    isOpen: Boolean,
    snackbarData: SnackbarData,
    modifier: Modifier,
    actionOnNewLine: Boolean,
) =
    with(snackbarData.visuals) {
        MdSnackbar(
            isOpen.isTrueOrNull(),
            actionText = actionLabel,
            timeout = duration.toMillis(),
            onAction = { snackbarData.performAction() },
            onClosed = { snackbarData.dismiss() },
            attrs = modifier.toAttrs(),
        ) {
            Text(message)
            if (withDismissAction) {
                // TODO The icon's color is incorrect. It should be white in a dark snackbar, instead it's black now.
                // No need to pass the `onClick` callback
                Icon(Icons.Default.Close, null, slotModifier(MdSnackbarScope.Slot.Icon))
            }
        }
    }

@ExperimentalApi
@Composable
actual fun Snackbar(optionalSnackbarArgs: OptionalSnackbarArgs) =
    when (optionalSnackbarArgs) {
        OptionalSnackbarArgs.Closed -> MdSnackbar(false) {}
        is OptionalSnackbarArgs.Open -> with(optionalSnackbarArgs) {
            CommonSnackbar(true, snackbarData, modifier, actionOnNewLine)
        }
    }
