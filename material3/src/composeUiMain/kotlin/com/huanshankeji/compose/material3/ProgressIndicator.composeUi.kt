package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material3.CircularProgressIndicator as PlatformCircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator as PlatformLinearProgressIndicator

@Composable
actual fun LinearProgressIndicator(progress: () -> Float, modifier: Modifier) =
    PlatformLinearProgressIndicator(progress, modifier.platformModifier)

@Composable
actual fun LinearProgressIndicator(modifier: Modifier) =
    PlatformLinearProgressIndicator(modifier.platformModifier)

@Composable
actual fun CircularProgressIndicator(progress: () -> Float, modifier: Modifier) =
    PlatformCircularProgressIndicator(progress, modifier.platformModifier)

@Composable
actual fun CircularProgressIndicator(modifier: Modifier) =
    PlatformCircularProgressIndicator(modifier.platformModifier)
