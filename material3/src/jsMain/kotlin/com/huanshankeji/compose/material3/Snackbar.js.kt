package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MaterialWebAdditionsApi
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSnackbar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSnackbarScope.Slot
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

// Note: [dismissAction] is not supported on JS DOM and is ignored.

@OptIn(MaterialWebAdditionsApi::class)
@Composable
actual fun Snackbar(
    modifier: Modifier,
    action: @Composable (() -> Unit)?,
    dismissAction: @Composable (() -> Unit)?,
    actionOnNewLine: Boolean,
    content: @Composable () -> Unit
) =
    MdSnackbar(
        open = true.isTrueOrNull(),
        twoLines = actionOnNewLine.isTrueOrNull(),
        attrs = modifier.toAttrs()
    ) {
        content()
        action?.let {
            Div({ slot(Slot.Action) }) { it() }
        }
    }

@OptIn(MaterialWebAdditionsApi::class)
@Composable
actual fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier,
    actionOnNewLine: Boolean,
    withDismissAction: Boolean
) {
    val visuals = snackbarData.visuals
    MdSnackbar(
        open = true.isTrueOrNull(),
        twoLines = actionOnNewLine.isTrueOrNull(),
        actionText = visuals.actionLabel,
        onAction = { snackbarData.performAction() },
        attrs = modifier.toAttrs()
    ) {
        Text(visuals.message)
    }
}

