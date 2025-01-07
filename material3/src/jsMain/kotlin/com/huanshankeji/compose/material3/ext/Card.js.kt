package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdElevatedCard
import com.huanshankeji.compose.html.material3.MdFilledCard
import com.huanshankeji.compose.html.material3.MdOutlinedCard
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs

@Composable
actual fun Card(modifier: Modifier, content: @Composable () -> Unit) =
    @OptIn(MaterialWebLabsApi::class)
    MdFilledCard(modifier.toAttrs()) { content() }

@Composable
actual fun ElevatedCard(modifier: Modifier, content: @Composable () -> Unit) =
    @OptIn(MaterialWebLabsApi::class)
    MdElevatedCard(modifier.toAttrs()) { content() }

@Composable
actual fun OutlinedCard(modifier: Modifier, content: @Composable () -> Unit) =
    @OptIn(MaterialWebLabsApi::class)
    MdOutlinedCard(modifier.toAttrs()) { content() }
