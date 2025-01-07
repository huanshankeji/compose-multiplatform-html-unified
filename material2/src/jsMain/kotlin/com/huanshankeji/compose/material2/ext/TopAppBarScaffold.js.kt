package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.contentDescription
import com.huanshankeji.compose.foundation.ext.matchPositionRelativeParent
import com.huanshankeji.compose.foundation.layout.Column
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.ext.fillMaxWidthStretch
import com.huanshankeji.compose.foundation.layout.fillMaxSize
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material2.icons.mdcIconWithStyle
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.textAlign
import dev.petuska.kmdc.top.app.bar.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

actual class NavigationIconScope(val mdcTopAppBarSectionScope: MDCTopAppBarSectionScope) {
    @Composable
    actual fun NavButton(onClick: () -> Unit, modifier: Modifier, content: @Composable () -> Unit) =
        mdcTopAppBarSectionScope.NavButton(attrs = modifier.toAttrs { onClick { onClick() } }) { content() }

    @Composable
    actual fun MaterialIconNavButton(onClick: () -> Unit, modifier: Modifier, icon: Icon, contentDescription: String?) =
        mdcTopAppBarSectionScope.NavButton(attrs = modifier.toAttrs {
            onClick { onClick() }
            mdcIconWithStyle()
            contentDescription(contentDescription)
        }) { Text(icon.name) }
}

actual class TopAppBarActionsScope(val mdcTopAppBarSectionScope: MDCTopAppBarSectionScope) {
    @Composable
    actual fun ActionButton(onClick: () -> Unit, modifier: Modifier, content: @Composable () -> Unit) =
        mdcTopAppBarSectionScope.ActionButton(attrs = modifier.toAttrs { onClick { onClick() } }) { content() }

    @Composable
    actual fun MaterialIconActionButton(
        onClick: () -> Unit, modifier: Modifier, icon: Icon, contentDescription: String?
    ) =
        mdcTopAppBarSectionScope.ActionButton(attrs = modifier.toAttrs {
            onClick { onClick() }
            mdcIconWithStyle()
            contentDescription(contentDescription)
        }) { Text(icon.name) }
}

@Composable
actual fun PrimitiveTopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    actions: @Composable TopAppBarActionsScope.() -> Unit,
    contentModifier: Modifier,
    content: @Composable () -> Unit
) =
    MDCTopAppBar {
        TopAppBar(topAppBarModifier.toAttrs()) {
            Row {
                Section(align = MDCTopAppBarSectionAlign.Start) {
                    navigationIcon?.let { NavigationIconScope(this@Section).it() }
                    Title { title() }
                }
                Section(
                    align = MDCTopAppBarSectionAlign.End,
                    attrs = {
                        attr("role", "toolbar")
                    }
                ) {
                    TopAppBarActionsScope(this).actions()
                }
            }
        }
        Main(contentModifier.toAttrs()) { content() }
    }

/**
 * It's highly recommended to read the KDoc in the common module.
 */
@Composable
actual fun TopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    actions: @Composable TopAppBarActionsScope.() -> Unit,
    bottomBar: @Composable (() -> Unit)?,
    snackbarHost: @Composable (() -> Unit)?,
    floatingActionButton: @Composable (() -> Unit)?,
    floatingActionButtonPosition: FabPosition,
    isFloatingActionButtonDockedComposeUi: Boolean,
    content: @Composable (PaddingValues) -> Unit
) {
    @Composable
    fun fabWithPosition(floatingActionButton: @Composable (() -> Unit)) =
        Div({
            style {
                position(Position.Absolute)
                bottom(16.px)
                when (floatingActionButtonPosition) {
                    FabPosition.Start -> left(16.px)
                    FabPosition.Center -> {
                        width(100.percent)
                        textAlign(TextAlign.Center)
                    }

                    FabPosition.End -> right(16.px)
                }
            }
        }) {
            floatingActionButton()
        }

    Column(Modifier.fillMaxSize()) {
        PrimitiveTopAppBarScaffold(
            title,
            topAppBarModifier,
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
                        //overflow(Overflow.Auto) // This seems not needed. TODO remove if confirmed to be not needed
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
