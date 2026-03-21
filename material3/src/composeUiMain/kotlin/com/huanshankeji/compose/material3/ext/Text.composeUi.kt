package com.huanshankeji.compose.material3.ext

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * @see com.huanshankeji.compose.material3.Text
 */
@Composable
actual fun TaglessText(text: String) =
    Text(text)

fun String?.toNullableTextComposable(): @Composable (() -> Unit)? =
    this?.let { { Text(it) } }
