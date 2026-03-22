package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/progress-indicators/overview
https://developer.android.com/develop/ui/compose/components/progress
 */

/**
 * Determinate
 */
@Composable
expect fun LinearProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
)

/**
 * Indeterminate
 */
@Composable
expect fun LinearProgressIndicator(
    modifier: Modifier = Modifier
)

/**
 * Determinate
 */
@Composable
expect fun CircularProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
)

/**
 * Indeterminate
 */
@Composable
expect fun CircularProgressIndicator(
    modifier: Modifier = Modifier
)
