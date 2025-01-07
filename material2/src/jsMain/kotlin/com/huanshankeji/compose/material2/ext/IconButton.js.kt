package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material2.icons.mdcIconWithStyle
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import dev.petuska.kmdc.icon.button.MDCIconButton
import org.jetbrains.compose.web.dom.Text

@Composable
actual fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    icon: Icon,
    contentDescription: String?
) {
    MDCIconButton(attrs = modifier.toAttrs {
        mdcIconWithStyle()
        onClick { onClick() }
    }) {
        Text(icon.name)
    }
}
