package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdNavigationDrawer
import com.huanshankeji.compose.html.material3.MdNavigationDrawerModal
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull

@MaterialWebLabsApi
@Composable
actual fun NavigationDrawer(
    opened: Boolean,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    MdNavigationDrawer(
        opened = opened.isTrueOrNull(),
        attrs = modifier.toAttrs(),
        content = { content() }
    )

@MaterialWebLabsApi
@Composable
actual fun ModalNavigationDrawer(
    opened: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    MdNavigationDrawerModal(
        opened = opened.isTrueOrNull(),
        attrs = modifier.toAttrs(),
        content = { content() }
    )
