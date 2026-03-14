package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.horizontalScroll
import com.huanshankeji.compose.foundation.layout.*
import com.huanshankeji.compose.foundation.verticalScroll
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun VerticalScrollBox(
    containerModifier: Modifier,
    contentModifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxScope.() -> Unit,
) =
    Box(containerModifier.verticalScroll(rememberScrollState()).then(contentModifier), contentAlignment, content)

@Composable
actual fun HorizontalScrollBox(
    containerModifier: Modifier,
    contentModifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxScope.() -> Unit,
) =
    Box(containerModifier.horizontalScroll(rememberScrollState()).then(contentModifier), contentAlignment, content)

@Composable
actual fun VerticalScrollColumn(
    containerModifier: Modifier,
    contentModifier: Modifier,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    content: @Composable ColumnScope.() -> Unit,
) =
    Column(
        containerModifier.verticalScroll(rememberScrollState()).then(contentModifier),
        verticalArrangement,
        horizontalAlignment,
        content,
    )

@Composable
actual fun HorizontalScrollRow(
    containerModifier: Modifier,
    contentModifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment: Alignment.Vertical,
    content: @Composable RowScope.() -> Unit,
) =
    Row(
        containerModifier.horizontalScroll(rememberScrollState()).then(contentModifier),
        horizontalArrangement,
        verticalAlignment,
        content,
    )
