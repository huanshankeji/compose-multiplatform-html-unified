package com.huanshankeji.compose.foundation

import com.huanshankeji.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.onClick

@ExperimentalFoundationApi
actual fun Modifier.onClick(onClick: () -> Unit): Modifier =
    platformModify { onClick { onClick() } }

@ExperimentalFoundationApi
actual fun Modifier.clickable(onClick: () -> Unit): Modifier =
    platformModify { onClick { onClick() } }
