package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdCenterAlignedTopAppBar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdLargeTopAppBar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdMediumTopAppBar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSmallTopAppBar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdTopAppBarScope
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Div

@Composable
private fun MdTopAppBarScope.TopAppBarContent(
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable () -> Unit,
) {
    navigationIcon?.let { navIcon ->
        Div({ slot(MdTopAppBarScope.Slot.Start) }) { navIcon() }
    }
    title()
    Div({ slot(MdTopAppBarScope.Slot.End) }) { actions() }
}

@Composable
actual fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable () -> Unit,
) =
    MdSmallTopAppBar(attrs = modifier.toAttrs()) {
        TopAppBarContent(title, navigationIcon, actions)
    }

@Composable
actual fun CenterAlignedTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable () -> Unit,
) =
    MdCenterAlignedTopAppBar(attrs = modifier.toAttrs()) {
        TopAppBarContent(title, navigationIcon, actions)
    }

@Composable
actual fun MediumTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable () -> Unit,
) =
    MdMediumTopAppBar(attrs = modifier.toAttrs()) {
        TopAppBarContent(title, navigationIcon, actions)
    }

@Composable
actual fun LargeTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable () -> Unit,
) =
    MdLargeTopAppBar(attrs = modifier.toAttrs()) {
        TopAppBarContent(title, navigationIcon, actions)
    }
