package com.huanshankeji.compose.material3.ext

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.huanshankeji.compose.ext.toNullableContentWithoutModifier
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier

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
) {
    if (isOpen)
        AlertDialog(
            onDismissRequest,
            confirmButton,
            modifier.platformModifier,
            dismissButton,
            icon.toNullableContentWithoutModifier(),
            title.toNullableContentWithoutModifier(),
            text.toNullableContentWithoutModifier(),
        )
}

@Composable
actual fun SimpleDialog(
    isOpen: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    if (isOpen)
    // There is another overload that pops up a dialog with a new window: `fun Dialog(onCloseRequest: () -> Unit, state: DialogState = ..., visible: Boolean = ..., title: String = ..., icon: Painter? = ..., undecorated: Boolean = ..., transparent: Boolean = ..., resizable: Boolean = ..., enabled: Boolean = ..., focusable: Boolean = ..., onPreviewKeyEvent: (KeyEvent) -> Boolean = ..., onKeyEvent: (KeyEvent) -> Boolean = ..., content: ComposableFunction1<DialogWindowScope, Unit>): Unit`
        Dialog(onDismissRequest = onDismissRequest) {
            // https://github.com/androidx/androidx/blob/7b7fd0452b07808a084aee6c3246d7fda11d5604/compose/material3/material3/src/commonMain/kotlin/androidx/compose/material3/AlertDialog.kt#L410
            Card(modifier.platformModifier.sizeIn(minWidth = 280.dp)) {
                Box(PlatformModifier.padding(16.dp)) { content() }
            }
        }
}
