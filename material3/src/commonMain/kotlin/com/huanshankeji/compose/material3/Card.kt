package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.ColumnScope
import com.huanshankeji.compose.material3.ext.Card
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun Card(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
)

/**
 * An alias for [Card].
 */
@Composable
fun FilledCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) =
    Card(modifier, content)

// TODO clickable cards

@Composable
expect fun ElevatedCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
)

@Composable
expect fun OutlinedCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
)
