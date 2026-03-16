package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.ext.matchPositionRelativeParent
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.ext.fillMaxSizeStretch
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.overflowX
import com.varabyte.kobweb.compose.css.overflowY
import com.varabyte.kobweb.compose.css.textAlign
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

// adapted from `TopAppBarScaffold.js.kt` in `com.huanshankeji.compose.material2.ext`

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
    @Composable
    fun FabWithPosition() =
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

                    FabPosition.End, FabPosition.EndOverlay -> right(16.px)
                }
            }
        }) {
            floatingActionButton()
        }

    // Scaffold container: flexbox column layout using stretch (not 100%) to avoid scrollbar overflow with padding/margin
    Div(Modifier.fillMaxSizeStretch().then(modifier).toAttrs {
        style {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            position(Position.Relative)
            overflowX(Overflow.Hidden)
            overflowY(Overflow.Hidden)
        }
    }) {
        topBar()

        // Main content area: fills remaining vertical space between top bar and bottom bar
        // This part has nested `Div`s but works. Adapted from the Material 2 TopAppBarScaffold JS implementation.
        Div({
            style {
                flexGrow(1)
                flexShrink(1)
                flexBasis(0.px)
                position(Position.Relative)
            }
        }) {
            Div({
                style {
                    matchPositionRelativeParent()
                }
            }) {
                // This nested `Div` is here so that a child using `fillMaxSizeStretch` works properly.
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

            // Snackbar host: absolutely positioned at bottom center, above FAB, matching Compose UI Scaffold behavior.
            // The bottom offset (88px) accounts for the FAB area: 16px (FAB bottom) + 56px (standard FAB height) + 16px (gap).
            Div({
                style {
                    position(Position.Absolute)
                    bottom(88.px)
                    left(0.px)
                    right(0.px)
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.Center)
                    property("pointer-events", "none")
                    property("z-index", "10")
                }
            }) {
                Div({
                    style {
                        property("pointer-events", "auto")
                    }
                }) {
                    snackbarHost()
                }
            }
        }

        bottomBar()

        // FAB for EndOverlay position: overlays bottom bar
        if (floatingActionButtonPosition == FabPosition.EndOverlay) {
            FabWithPosition()
        }
    }
}
