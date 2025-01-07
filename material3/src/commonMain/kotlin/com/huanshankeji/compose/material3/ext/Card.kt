package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun Card(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

/**
 * An alias for [Card].
 */
@Composable
fun FilledCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) =
    Card(modifier, content)

@Composable
expect fun ElevatedCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

@Composable
expect fun OutlinedCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)
