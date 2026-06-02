package com.huanshankeji.compose.foundation.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.BoxScope
import com.huanshankeji.compose.ui.Modifier

// Also consider moving these to `com.huanshankeji.compose.foundation.layout.ext` because they depend on `Box` on Compose UI which is in the `layout` package.

@Composable
expect fun VerticalScrollContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
)

@Composable
expect fun HorizontalScrollContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
)
