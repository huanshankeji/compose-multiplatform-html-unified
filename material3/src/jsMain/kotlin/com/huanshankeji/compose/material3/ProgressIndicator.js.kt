package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdCircularProgress
import com.huanshankeji.compose.html.material3.MdLinearProgress
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs

@Composable
actual fun LinearProgressIndicator(progress: () -> Float, modifier: Modifier) =
    MdLinearProgress(value = progress(), attrs = modifier.toAttrs())

@Composable
actual fun LinearProgressIndicator(modifier: Modifier) =
    MdLinearProgress(indeterminate = true, attrs = modifier.toAttrs())

@Composable
actual fun CircularProgressIndicator(progress: () -> Float, modifier: Modifier) =
    MdCircularProgress(value = progress(), attrs = modifier.toAttrs())

@Composable
actual fun CircularProgressIndicator(modifier: Modifier) =
    MdCircularProgress(indeterminate = true, attrs = modifier.toAttrs())
