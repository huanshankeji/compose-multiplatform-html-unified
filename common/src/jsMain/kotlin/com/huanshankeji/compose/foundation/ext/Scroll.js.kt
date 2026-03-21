package com.huanshankeji.compose.foundation.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.horizontalScroll
import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.foundation.layout.BoxScope
import com.huanshankeji.compose.foundation.rememberScrollState
import com.huanshankeji.compose.foundation.verticalScroll
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun VerticalScrollContainer(
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit,
) =
    Box(modifier.verticalScroll(rememberScrollState()), content = content)

@Composable
actual fun HorizontalScrollContainer(
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit,
) =
    Box(modifier.horizontalScroll(rememberScrollState()), content = content)
