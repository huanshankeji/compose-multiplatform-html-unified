package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.*
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import com.varabyte.kobweb.compose.css.BoxSizing
import com.varabyte.kobweb.compose.css.boxSizing
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement

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

// The Material Web Additions top app bar components have `width: 100%` and `padding-inline: 16px` in their shadow DOM CSS
// but no `box-sizing: border-box`, causing the total width to overflow by 32px. This workaround fixes it.
// See: https://github.com/maicol07/material-web-additions/blob/096590484ce31dfb18d8e2d1998989ed933328e1/top-app-bar/internal/_shared.scss#L10
private val boxSizingBorderBoxStyleHandler: StyleScope.() -> Unit = {
    boxSizing(BoxSizing.BorderBox)
}
private val boxSizingBorderBoxAttrs: AttrsScope<HTMLElement>.() -> Unit = {
    style(boxSizingBorderBoxStyleHandler)
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
        attrs = modifier.toAttrs(boxSizingBorderBoxAttrs),
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
        attrs = modifier.toAttrs(boxSizingBorderBoxAttrs),
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
        attrs = modifier.toAttrs(boxSizingBorderBoxAttrs),
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
        attrs = modifier.toAttrs(boxSizingBorderBoxAttrs),
    ) {
        TopAppBarContent(title, navigationIcon, actions)
    }
