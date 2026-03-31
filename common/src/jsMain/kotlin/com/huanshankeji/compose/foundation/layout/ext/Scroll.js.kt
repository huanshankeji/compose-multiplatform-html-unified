package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.ext.HorizontalScrollContainer
import com.huanshankeji.compose.foundation.ext.VerticalScrollContainer
import com.huanshankeji.compose.foundation.layout.*
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun VerticalScrollBox(
    containerModifier: Modifier,
    contentModifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxScope.() -> Unit,
) =
    VerticalScrollContainer(containerModifier) {
        KobwebBox(contentModifier, contentAlignment, content)
    }

@Composable
actual fun HorizontalScrollBox(
    containerModifier: Modifier,
    contentModifier: Modifier,
    contentAlignment: Alignment,
    content: @Composable BoxScope.() -> Unit,
) =
    HorizontalScrollContainer(containerModifier) {
        KobwebBox(contentModifier, contentAlignment, content)
    }

@Composable
actual fun VerticalScrollColumn(
    containerModifier: Modifier,
    contentModifier: Modifier,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    content: @Composable ColumnScope.() -> Unit,
) =
    VerticalScrollContainer(containerModifier) {
        Column(contentModifier, verticalArrangement, horizontalAlignment, content)
    }

@Composable
actual fun HorizontalScrollRow(
    containerModifier: Modifier,
    contentModifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment: Alignment.Vertical,
    content: @Composable RowScope.() -> Unit,
) =
    HorizontalScrollContainer(containerModifier) {
        Row(contentModifier, horizontalArrangement, verticalAlignment, content)
    }
