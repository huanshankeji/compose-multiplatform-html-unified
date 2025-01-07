package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Card(modifier: Modifier, content: @Composable () -> Unit) =
    com.huanshankeji.compose.material3.Card(modifier) { content() }

@Composable
actual fun ElevatedCard(modifier: Modifier, content: @Composable () -> Unit) =
    com.huanshankeji.compose.material3.ElevatedCard(modifier) { content() }

@Composable
actual fun OutlinedCard(modifier: Modifier, content: @Composable () -> Unit) =
    com.huanshankeji.compose.material3.OutlinedCard(modifier) { content() }
