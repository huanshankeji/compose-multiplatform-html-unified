package com.huanshankeji.compose.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.foundation.ext.css.horizontalScroll
import com.huanshankeji.compose.foundation.ext.css.verticalScroll
import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.foundation.layout.BoxScope
import com.huanshankeji.compose.foundation.layout.ext.KobwebBox
import com.huanshankeji.compose.foundation.layout.ext.cssHeightStretchValueBrowserDependent
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.kobweb.compose.ui.modifiers.imitateComposeUiLayout
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.css.maxHeight

/**
 * Applies `overflow-y: auto` and `max-height: stretch` (or browser-specific equivalent) so that
 * the element scrolls vertically within the available height of its parent.
 *
 * The `max-height` constraint is applied here (rather than in [imitateComposeUiLayout]) to avoid
 * hiding content in non-scrollable Column/Row/Box layouts.
 */
fun PlatformModifier.verticalScroll() =
    styleModifier {
        maxHeight(cssHeightStretchValueBrowserDependent)
        verticalScroll()
    }

fun PlatformModifier.horizontalScroll() =
    styleModifier { horizontalScroll() }

val imitateComposeUiLayoutVerticalScrollPlatformModifier = PlatformModifier.imitateComposeUiLayout().verticalScroll()
val imitateComposeUiLayoutHorizontalScrollPlatformModifier =
    PlatformModifier.imitateComposeUiLayout().horizontalScroll()


@Composable
actual fun rememberScrollState(initial: Int): ScrollState =
    ScrollState

@Stable
actual object ScrollState

actual fun Modifier.verticalScroll(state: ScrollState): Modifier =
    platformModify { verticalScroll() }

actual fun Modifier.horizontalScroll(state: ScrollState): Modifier =
    platformModify { horizontalScroll() }

@Composable
actual fun VerticalScrollBox(
    boxModifier: Modifier,
    contentModifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxScope.() -> Unit,
) =
    Box(boxModifier.verticalScroll(rememberScrollState())) {
        KobwebBox(contentModifier, contentAlignment, content)
    }

@Composable
actual fun HorizontalScrollBox(
    boxModifier: Modifier,
    contentModifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxScope.() -> Unit,
) =
    Box(boxModifier.horizontalScroll(rememberScrollState())) {
        KobwebBox(contentModifier, contentAlignment, content)
    }
