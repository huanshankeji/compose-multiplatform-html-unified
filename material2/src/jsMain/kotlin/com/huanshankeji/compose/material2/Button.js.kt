package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.material2.ext.ButtonType
import com.huanshankeji.compose.material2.ext.toMDCButtonType
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import dev.petuska.kmdc.button.MDCButton
import dev.petuska.kmdc.button.MDCButtonScope
import org.w3c.dom.HTMLButtonElement

@Composable
internal fun CommonButton(
    onClick: () -> Unit,
    buttonType: ButtonType,
    modifier: Modifier,
    content: @Composable MDCButtonScope<HTMLButtonElement>.() -> Unit
) =
    MDCButton(
        buttonType.toMDCButtonType(),
        attrs = modifier.toAttrs {
            onClick { onClick() }
        },
        content = content
    )

@Composable
private fun Button(
    onClick: () -> Unit,
    buttonType: ButtonType,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) =
    CommonButton(onClick, buttonType, modifier) {
        Row(content = content)
    }

@Composable
actual fun Button(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) =
    Button(onClick, ButtonType.Contained, modifier, content)

@Composable
actual fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) =
    Button(onClick, ButtonType.Outlined, modifier, content)

@Composable
actual fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) =
    Button(onClick, ButtonType.Text, modifier, content)
