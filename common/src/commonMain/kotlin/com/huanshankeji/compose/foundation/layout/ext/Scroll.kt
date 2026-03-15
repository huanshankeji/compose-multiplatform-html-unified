package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Arrangement
import com.huanshankeji.compose.foundation.layout.BoxScope
import com.huanshankeji.compose.foundation.layout.ColumnScope
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun VerticalScrollBox(
    containerModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
)

@Composable
expect fun HorizontalScrollBox(
    containerModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
)

@Composable
expect fun VerticalScrollColumn(
    containerModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
)

@Composable
expect fun HorizontalScrollRow(
    containerModifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit,
)
