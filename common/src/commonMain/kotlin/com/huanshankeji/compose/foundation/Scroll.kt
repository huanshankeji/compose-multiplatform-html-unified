package com.huanshankeji.compose.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.foundation.ext.HorizontalScrollContainer
import com.huanshankeji.compose.foundation.ext.VerticalScrollContainer
import com.huanshankeji.compose.foundation.layout.BoxScope
import com.huanshankeji.compose.foundation.layout.ext.HorizontalScrollRow
import com.huanshankeji.compose.foundation.layout.ext.VerticalScrollBox
import com.huanshankeji.compose.foundation.layout.ext.VerticalScrollColumn
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun rememberScrollState(initial: Int = 0): ScrollState

/**
 * Not working on JS yet and delegating to [Unit].
 */
@Stable
expect class ScrollState

const val SCROLL_MODIFIER_DEPRECATION_MESSAGE =
    "The scroll modifier does not produce completely consistent visual results on both platforms, " +
            "and has a bug that it squeezes its content to size zero on JS DOM because of `maxWidth/Height(cssWidthStretchValueBrowserDependent)` used with `overflowY/X(Overflow.Auto)`, " +
            "which is then used by the layout components `Box`, `Column`, and `Row`. " +
            "Use `VerticalScrollContainer`, `VerticalScrollBox`, `VerticalScrollColumn` for vertical Scroll instead, " +
            "or `HorizontalScrollContainer`, `HorizontalScrollBox`, `HorizontalScrollRow` for horizontal Scroll instead, " +
            "to wrap the component instead."

/*
It seems `state` has to be achieved with `DisposableEffect` on JS which can not be set with the Kobweb `Modifier` yet.
See https://developer.mozilla.org/en-US/docs/Web/API/Element/scrollTop.
 */
/**
 * Note that if the component is a layout, for example a Material card,
 * it applies to the target as a whole on `androidx` targets, but applies to its content on JS DOM.
 * For consistency on different platforms, components such as [VerticalScrollContainer], [VerticalScrollBox], [VerticalScrollColumn] are recommended over this modifier.
 */
@Deprecated(SCROLL_MODIFIER_DEPRECATION_MESSAGE)
@ExperimentalApi
expect fun Modifier.verticalScroll(
    state: ScrollState
    /*
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false
    */
): Modifier

/**
 * For consistency on different platforms, components such as [HorizontalScrollContainer], [HorizontalScrollBox], [HorizontalScrollRow] are recommended over this modifier.
 * @see verticalScroll
 */
@Deprecated(SCROLL_MODIFIER_DEPRECATION_MESSAGE)
@ExperimentalApi
expect fun Modifier.horizontalScroll(state: ScrollState): Modifier

@Deprecated(
    "Moved to the `layout.ext` package.",
    ReplaceWith(
        "VerticalScrollBox(boxModifier, contentModifier, contentAlignment, content)",
        "com.huanshankeji.compose.foundation.layout.ext.VerticalScrollBox"
    )
)
@Composable
fun VerticalScrollBox(
    boxModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
) =
    com.huanshankeji.compose.foundation.layout.ext.VerticalScrollBox(
        boxModifier,
        contentModifier,
        contentAlignment,
        content
    )

@Deprecated(
    "Moved to the `layout.ext` package.",
    ReplaceWith(
        "HorizontalScrollBox(boxModifier, contentModifier, contentAlignment, content)",
        "com.huanshankeji.compose.foundation.layout.ext.HorizontalScrollBox"
    )
)
@Composable
fun HorizontalScrollBox(
    boxModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
) =
    com.huanshankeji.compose.foundation.layout.ext.HorizontalScrollBox(
        boxModifier,
        contentModifier,
        contentAlignment,
        content
    )
