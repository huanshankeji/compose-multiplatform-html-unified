package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.html.material3.MdFab
import com.huanshankeji.compose.html.material3.MdFabScope
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.compose.ui.toCommonModifier
import com.varabyte.kobweb.compose.ui.attrsModifier

internal val MdFabScope.slotEqIconModifier
    get() = PlatformModifier.attrsModifier { slotEqIcon() }.toCommonModifier()

private fun (@Composable () -> Unit).toBoxedContentWithModifier(): @Composable (Modifier) -> Unit =
    { modifier ->
        // The content doesn't show without `slot="icon"` set.
        // TODO consider using the Kobweb `Box` after resolving the issue whether `fit-content` is needed on the components
        Box(modifier) {
            this@toBoxedContentWithModifier()
        }
    }


@Composable
actual fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    com.huanshankeji.compose.material3.ext.FloatingActionButton(
        onClick, modifier, content.toBoxedContentWithModifier()
    )

@Composable
actual fun SmallFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    com.huanshankeji.compose.material3.ext.SmallFloatingActionButton(
        onClick, modifier, content.toBoxedContentWithModifier()
    )

@Composable
actual fun LargeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    com.huanshankeji.compose.material3.ext.LargeFloatingActionButton(
        onClick, modifier, content.toBoxedContentWithModifier()
    )

@Composable
actual fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) =
    MdFab(attrs = modifier.toCommonButtonAttrs(onClick)) {
        // TODO consider using the Kobweb `Row` after resolving the issue whether `fit-content` is needed on the components
        Row(slotEqIconModifier) { content() }
    }
