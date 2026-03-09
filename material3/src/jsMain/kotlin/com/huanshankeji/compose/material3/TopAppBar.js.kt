package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.*
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Div

@Composable
private fun MdTopAppBarScope.TopAppBarContent(
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (() -> Unit)?,
) {
    navigationIcon?.let {
        Div({ slot(MdTopAppBarScope.Slot.Start) }) { it() }
    }
    title()
    actions?.let {
        Div({ slot(MdTopAppBarScope.Slot.End) }) { it() }
    }
}

@Composable
actual fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (() -> Unit)?,
    stickyJsDom: Boolean,
) =
    MdSmallTopAppBar(
        stickyJsDom.isTrueOrNull(),
        attrs = modifier.toAttrs(),
    ) {
        TopAppBarContent(title, navigationIcon, actions)
    }

@Composable
actual fun CenterAlignedTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (() -> Unit)?,
    stickyJsDom: Boolean,
) =
    MdCenterAlignedTopAppBar(
        stickyJsDom.isTrueOrNull(),
        attrs = modifier.toAttrs(),
    ) {
        TopAppBarContent(title, navigationIcon, actions)
    }

@Composable
actual fun MediumTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (() -> Unit)?,
    stickyJsDom: Boolean,
) =
    MdMediumTopAppBar(
        stickyJsDom.isTrueOrNull(),
        attrs = modifier.toAttrs(),
    ) {
        TopAppBarContent(title, navigationIcon, actions)
    }

@Composable
actual fun LargeTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (() -> Unit)?,
    stickyJsDom: Boolean,
) =
    MdLargeTopAppBar(
        stickyJsDom.isTrueOrNull(),
        attrs = modifier.toAttrs(),
    ) {
        TopAppBarContent(title, navigationIcon, actions)
    }
