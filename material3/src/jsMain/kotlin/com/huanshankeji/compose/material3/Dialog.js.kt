package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.huanshankeji.compose.html.material3.MdDialog
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Div

@Composable
actual fun AlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier,
    dismissButton: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?
) {
    var isOpen by remember { mutableStateOf(true) }
    
    if (isOpen) {
        MdDialog(
            open = true,
            attrs = modifier.toAttrs()
        ) {
            title?.let { titleContent ->
                Div({
                    slotEqHeadline()
                }) {
                    icon?.let { iconContent -> iconContent() }
                    titleContent()
                }
            }
            
            text?.let { textContent ->
                Div({
                    slotEqContent()
                }) {
                    textContent()
                }
            }
            
            Div({
                slotEqActions()
            }) {
                dismissButton?.let { dismissBtn -> dismissBtn() }
                confirmButton()
            }
        }
    }
}
