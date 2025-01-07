package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdFab
import com.huanshankeji.compose.html.material3.MdFabScope
import com.huanshankeji.compose.material3.slotEqIconModifier
import com.huanshankeji.compose.material3.toCommonButtonAttrs
import com.huanshankeji.compose.ui.Modifier

private fun (@Composable (Modifier) -> Unit).toCommonMdFabContent(): @Composable MdFabScope.() -> Unit =
    { this@toCommonMdFabContent(slotEqIconModifier) }

@Composable
actual fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable (Modifier) -> Unit
) =
    MdFab(attrs = modifier.toCommonButtonAttrs(onClick), content = content.toCommonMdFabContent())

@Composable
actual fun SmallFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable (Modifier) -> Unit
) =
    MdFab(size = "small", attrs = modifier.toCommonButtonAttrs(onClick), content = content.toCommonMdFabContent())

@Composable
actual fun LargeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable (Modifier) -> Unit
) =
    MdFab(size = "large", attrs = modifier.toCommonButtonAttrs(onClick), content = content.toCommonMdFabContent())

@Composable
actual fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    label: String,
    content: @Composable ((Modifier) -> Unit)?
) =
    MdFab(label = label, attrs = modifier.toCommonButtonAttrs(onClick), content = content?.toCommonMdFabContent())
