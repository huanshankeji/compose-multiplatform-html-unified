package com.huanshankeji.compose.foundation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.foundation.layout.BoxScope
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun rememberScrollState(initial: Int): ScrollState =
    rememberScrollState(initial)

actual typealias ScrollState = androidx.compose.foundation.ScrollState

actual fun Modifier.verticalScroll(state: ScrollState): Modifier =
    platformModify { verticalScroll(state) }

actual fun Modifier.horizontalScroll(state: ScrollState): Modifier =
    platformModify { horizontalScroll(state) }

@Composable
actual fun VerticalScrollBox(
    boxModifier: Modifier,
    contentModifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxScope.() -> Unit
) =
    Box(boxModifier.verticalScroll(rememberScrollState()).then(contentModifier), contentAlignment, content)

@Composable
actual fun HorizontalScrollBox(
    boxModifier: Modifier,
    contentModifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxScope.() -> Unit
) =
    Box(boxModifier.horizontalScroll(rememberScrollState()).then(contentModifier), contentAlignment, content)
