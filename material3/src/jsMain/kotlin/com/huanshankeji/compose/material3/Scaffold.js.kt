package com.huanshankeji.compose.material3

import androidx.compose.runtime.*
import com.huanshankeji.compose.foundation.ext.matchPositionRelativeParent
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.ext.fillMaxSizeStretch
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.varabyte.kobweb.browser.dom.observers.ResizeObserver
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.textAlign
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

// Adapted from `TopAppBarScaffold.js.kt` in `com.huanshankeji.compose.material2.ext`.
/*
The majority of the code below has been adapted by Copilot to provide consistent visual results with Compose UI.
It's tested manually to verify that the visual effects match, but has not been reviewed line by line by a human reviewer.
 */

// TODO Can this be implemented using the `Box`, Column` wrappers instead of HTML and CSS primitives?

private const val SCAFFOLD_SPACING_PX = 16
private val scaffoldSpacingPx = SCAFFOLD_SPACING_PX.px

@Composable
actual fun Scaffold(
    modifier: Modifier,
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    snackbarHost: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit,
    floatingActionButtonPosition: FabPosition,
    content: @Composable (PaddingValues) -> Unit,
) {
    var fabHeight by remember { mutableStateOf(0) }
    var bottomBarHeight by remember { mutableStateOf(0) }

    @Composable
    fun FabWithPosition() =
        Div({
            style {
                position(Position.Absolute)
                bottom(scaffoldSpacingPx)
                when (floatingActionButtonPosition) {
                    FabPosition.Start -> left(scaffoldSpacingPx)
                    FabPosition.Center -> {
                        width(100.percent)
                        textAlign(TextAlign.Center)
                    }

                    FabPosition.End, FabPosition.EndOverlay -> right(scaffoldSpacingPx)
                }
            }
            ref { element ->
                val resizeObserver = ResizeObserver { entries, _ ->
                    fabHeight = entries.single().target.clientHeight
                }
                resizeObserver.observe(element)
                onDispose {
                    resizeObserver.disconnect()
                    fabHeight = 0
                }
            }
        }) {
            floatingActionButton()
        }

    /*
    Snackbar bottom offset from the content area bottom, following Compose UI's `ScaffoldLayout` logic:
    - Without FAB: a spacing gap above the bottom bar.
    - With FAB (non-EndOverlay): above the FAB with a spacing gap, inside the content area.
    - With FAB (EndOverlay): above the FAB with a spacing gap, accounting for the bottom bar overlay.
     */
    val snackbarBottomPx = if (fabHeight > 0) {
        val fabTopFromScaffoldBottom = SCAFFOLD_SPACING_PX + fabHeight + SCAFFOLD_SPACING_PX
        if (floatingActionButtonPosition != FabPosition.EndOverlay)
            fabTopFromScaffoldBottom
        else
            maxOf(0, fabTopFromScaffoldBottom - bottomBarHeight)
    } else {
        SCAFFOLD_SPACING_PX
    }

    Div(Modifier.fillMaxSizeStretch().then(modifier).toAttrs {
        style {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            position(Position.Relative)
        }
    }) {
        topBar()

        // Main content area: fills remaining vertical space between top bar and bottom bar.
        // Adapted from the Material 2 `TopAppBarScaffold` JS implementation.
        Div({
            style {
                flexGrow(1)
                flexBasis(0.px)
                position(Position.Relative)
            }
        }) {
            Div({
                style {
                    matchPositionRelativeParent()
                }
            }) {
                /*
                TODO See https://issues.chromium.org/issues/386052671. This can be removed when the issue is fixed,
                 possibly by partially reverting commit fb269bdfa876bc63402c68a025325f42b8a8abec.
                 This nested `Div` is here so that a child using `fillMaxSizeStretch` works properly.
                 `fillMaxSizeStretch` seems buggy when used directly in the `position: absolute` parent.
                 */
                Div({
                    style {
                        height(100.percent)
                    }
                }) {
                    content(PaddingValues())
                }
            }

            // FAB for Start/Center/End positions: inside content area, above bottom bar
            if (floatingActionButtonPosition != FabPosition.EndOverlay) {
                FabWithPosition()
            }

            // Snackbar host: positioned above the FAB (or above the bottom bar when there is no FAB)
            Div({
                style {
                    position(Position.Absolute)
                    bottom(snackbarBottomPx.px)
                    left(0.px)
                    right(0.px)
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.Center)
                }
            }) {
                snackbarHost()
            }
        }

        if (floatingActionButtonPosition == FabPosition.EndOverlay) {
            /*
            Wrap the bottom bar to measure its height for EndOverlay snackbar positioning.
            `display: flex` with `flex-direction: column` is needed so that children (e.g. inline `<span>` from `Text`)
            become flex items where CSS `width`/`height` properties apply, matching the scaffold's flex column behavior.
             */
            Div({
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                }
                ref { element ->
                    val resizeObserver = ResizeObserver { entries, _ ->
                        bottomBarHeight = entries.single().target.clientHeight
                    }
                    resizeObserver.observe(element)
                    onDispose {
                        resizeObserver.disconnect()
                        bottomBarHeight = 0
                    }
                }
            }) {
                bottomBar()
            }
        } else {
            bottomBar()
        }

        // FAB for EndOverlay position: overlays bottom bar
        if (floatingActionButtonPosition == FabPosition.EndOverlay) {
            FabWithPosition()
        }
    }
}
