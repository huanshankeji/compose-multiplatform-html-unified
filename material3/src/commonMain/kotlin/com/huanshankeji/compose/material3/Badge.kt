package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design badge.
 *
 * Note: This uses experimental Material Web labs components on the JS platform.
 *
 * @see <a href="https://m3.material.io/components/badge/overview">Material Design badge</a>
 * @see androidx.compose.material3.Badge
 */
@Composable
expect fun Badge(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)? = null
)
