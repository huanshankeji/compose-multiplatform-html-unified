package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSnackbar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSnackbarScope
import com.huanshankeji.compose.material.icons.Icons
import com.huanshankeji.compose.material.icons.filled.Close
import com.huanshankeji.compose.material3.ext.slotModifier
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Text

@Composable
actual fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier,
    actionOnNewLine: Boolean,
) =
    with(snackbarData.visuals) {
        MdSnackbar(
            // TODO Check whether we control the show state with this or an if statement.
            open = true,
            actionText = actionLabel,
            timeout = duration.toMillis(),
            onAction = { snackbarData.performAction() },
            attrs = modifier.toAttrs(),
        ) {
            Text(message)
            if (withDismissAction) {
                // No need to pass the `onClick` callback
                Icon(Icons.Default.Close, null, slotModifier(MdSnackbarScope.Slot.Icon))
            }
        }
    }
