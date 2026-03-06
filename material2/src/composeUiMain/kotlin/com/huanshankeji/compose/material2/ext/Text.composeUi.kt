package com.huanshankeji.compose.material2.ext

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
actual fun TaglessText(text: String) =
    Text(text)

fun String?.toNullableTextComposable(): @Composable (() -> Unit)? =
    this?.let { { Text(it) } }
