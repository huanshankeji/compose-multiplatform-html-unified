package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.ext.matchPositionRelativeParent
import com.huanshankeji.compose.foundation.layout.Column
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.ext.fillMaxWidthStretch
import com.huanshankeji.compose.foundation.layout.fillMaxSize
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MaterialWebAdditionsApi
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdCenterAlignedTopAppBar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdLargeTopAppBar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdMediumTopAppBar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdSmallTopAppBar
import com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdTopAppBarScope.Slot
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material3.IconButton
import com.huanshankeji.compose.material3.ext.TopAppBarType.CenterAligned
import com.huanshankeji.compose.material3.ext.TopAppBarType.Large
import com.huanshankeji.compose.material3.ext.TopAppBarType.Medium
import com.huanshankeji.compose.material3.ext.TopAppBarType.Small
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.textAlign
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLElement

actual class NavigationIconScope(val elementScope: ElementScope<HTMLElement>) {
    @Composable
    actual fun NavButton(onClick: () -> Unit, modifier: Modifier, content: @Composable () -> Unit) =
        IconButton(onClick, modifier, content = content)

    @Composable
    actual fun MaterialIconNavButton(
        onClick: () -> Unit, modifier: Modifier, icon: Icon, contentDescription: String?
    ) =
        IconButton(onClick, modifier) {
            com.huanshankeji.compose.material3.Icon(icon, contentDescription)
        }
}

actual class TopAppBarActionsScope(val elementScope: ElementScope<HTMLElement>) {
    @Composable
    actual fun ActionButton(onClick: () -> Unit, modifier: Modifier, content: @Composable () -> Unit) =
        IconButton(onClick, modifier, content = content)

    @Composable
    actual fun MaterialIconActionButton(
        onClick: () -> Unit, modifier: Modifier, icon: Icon, contentDescription: String?
    ) =
        IconButton(onClick, modifier) {
            com.huanshankeji.compose.material3.Icon(icon, contentDescription)
        }
}

@OptIn(MaterialWebAdditionsApi::class)
@Composable
private fun MdTopAppBar(
    topAppBarType: TopAppBarType,
    topAppBarModifier: Modifier,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    title: @Composable () -> Unit,
    actions: @Composable TopAppBarActionsScope.() -> Unit
) {
    val content: @Composable com.huanshankeji.compose.html.material3.maicol07.materialwebadditions.MdTopAppBarScope.() -> Unit =
        {
            navigationIcon?.let { navIconContent ->
                Div({ slot(Slot.Start) }) {
                    NavigationIconScope(this@Div).navIconContent()
                }
            }
            title()
            Div({ slot(Slot.End) }) {
                TopAppBarActionsScope(this@Div).actions()
            }
        }
    when (topAppBarType) {
        Small -> MdSmallTopAppBar(attrs = topAppBarModifier.toAttrs(), content = content)
        CenterAligned -> MdCenterAlignedTopAppBar(attrs = topAppBarModifier.toAttrs(), content = content)
        Medium -> MdMediumTopAppBar(attrs = topAppBarModifier.toAttrs(), content = content)
        Large -> MdLargeTopAppBar(attrs = topAppBarModifier.toAttrs(), content = content)
    }
}

@Composable
actual fun PrimitiveTopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier,
    topAppBarType: TopAppBarType,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    actions: @Composable TopAppBarActionsScope.() -> Unit,
    contentModifier: Modifier,
    content: @Composable () -> Unit
) {
    MdTopAppBar(topAppBarType, topAppBarModifier, navigationIcon, title, actions)
    Div(contentModifier.toAttrs()) { content() }
}

/**
 * It's highly recommended to read the KDoc in the common module.
 */
@Composable
actual fun TopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier,
    topAppBarType: TopAppBarType,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    actions: @Composable TopAppBarActionsScope.() -> Unit,
    bottomBar: @Composable (() -> Unit)?,
    snackbarHost: @Composable (() -> Unit)?,
    floatingActionButton: @Composable (() -> Unit)?,
    floatingActionButtonPosition: FabPosition,
    content: @Composable (PaddingValues) -> Unit
) {
    @Composable
    fun fabWithPosition(floatingActionButton: @Composable (() -> Unit)) =
        Div({
            style {
                position(Position.Absolute)
                bottom(16.px)
                when (floatingActionButtonPosition) {
                    FabPosition.Center -> {
                        width(100.percent)
                        textAlign(TextAlign.Center)
                    }

                    FabPosition.End, FabPosition.EndOverlay -> right(16.px)
                }
            }
        }) {
            floatingActionButton()
        }

    Column(Modifier.fillMaxSize()) {
        PrimitiveTopAppBarScaffold(
            title,
            topAppBarModifier,
            topAppBarType,
            navigationIcon,
            actions,
            Modifier.weight(1f).fillMaxWidthStretch()
        ) {
            // This part has a lot of nested `Div`s but works. Do not change unless you are sure that expected behavior is not broken.

            // The content gets hidden behind the top app bar if this div is not added.
            Div({
                style {
                    height(100.percent)
                    position(Position.Relative) // same issue as above if not added
                }
            }) {
                Div({
                    style {
                        matchPositionRelativeParent()
                    }
                }) {
                    // TODO See https://issues.chromium.org/issues/386052671. This can be removed when the issue is fixed, possibly by partially reverting commit fb269bdfa876bc63402c68a025325f42b8a8abec.
                    // This nested `Div` is here so that a child using `fillMaxSizeStretch` works properly. `fillMaxSizeStretch` seems buggy when used directly in the `position: absolute` parent.
                    Div({
                        style {
                            height(100.percent)
                        }
                    }) {
                        // see `ScaffoldLayoutWithMeasureFix`
                        val innerPadding = PaddingValues()
                        content(innerPadding)
                    }
                }

                floatingActionButton?.let { fabWithPosition(it) }
            }
        }

        bottomBar?.invoke()
    }
    snackbarHost?.let { it() }
}
