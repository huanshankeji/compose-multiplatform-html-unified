package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSnackbar
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Text

@Composable
actual fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier,
    actionOnNewLine: Boolean,
) =
    MdSnackbar(
        // TODO Check whether we control the show state with this or an if statement.
        open = true,
        actionText = snackbarData.visuals.actionLabel,
        timeout = snackbarData.visuals.duration.toMillis(),
        onAction = { snackbarData.performAction() },
        attrs = modifier.toAttrs(),
    ) {
        Text(snackbarData.visuals.message)
    }
