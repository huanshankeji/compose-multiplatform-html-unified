package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/progress-indicators/overview
https://developer.android.com/develop/ui/compose/components/progress
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-linear-progress-indicator.html
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-circular-progress-indicator.html
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
