package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import dev.petuska.kmdc.icon.button.MDCIconButton

@Composable
actual fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    MDCIconButton(attrs = modifier.toAttrs {
        onClick { onClick() }
    }) { content() }

/*
@Composable
actual fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    // `MDCIconButtonToggle` is not a composable
    TODO() as Unit
*/
