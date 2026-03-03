package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.DialogType
import com.huanshankeji.compose.html.material3.MdDialog
import com.huanshankeji.compose.html.material3.MdDialogScope.Slot
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.attrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Div

@Composable
actual fun AlertDialog(
    isOpen: Boolean,
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier,
    dismissButton: @Composable (() -> Unit)?,
    icon: @Composable ((Modifier) -> Unit)?,
    title: @Composable ((Modifier) -> Unit)?,
    text: @Composable ((Modifier) -> Unit)?,
) =
    MdDialog(
        type = DialogType.Alert,
        open = isOpen.isTrueOrNull(),
        onCancel = onDismissRequest,
        attrs = modifier.toAttrs(),
    ) {
        nullableContentWithSlot(title, Slot.Headline)
        nullableContentWithSlot(icon, Slot.Icon)
        nullableContentWithSlot(text, Slot.Content)

        Div({ slot(Slot.Actions) }) {
            dismissButton?.invoke()
            confirmButton()
        }
    }

@Composable
actual fun SimpleDialog(
    isOpen: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit,
) =
    MdDialog(
        open = isOpen.isTrueOrNull(),
        onCancel = onDismissRequest,
        attrs = modifier.toAttrs()
    ) {
        Div(attrs { slot(Slot.Content) }) {
            content()
        }
    }
