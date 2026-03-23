package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://developer.android.com/develop/ui/compose/components/divider
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-horizontal-divider.html
 */

/**
 * Material Design divider.
 *
 * @see <a href="https://m3.material.io/components/divider/overview">Material Design divider</a>
 * @see androidx.compose.material3.HorizontalDivider
 */
@Composable
expect fun HorizontalDivider(
    modifier: Modifier = Modifier
)
