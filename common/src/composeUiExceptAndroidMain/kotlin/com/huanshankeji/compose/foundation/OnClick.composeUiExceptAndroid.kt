package com.huanshankeji.compose.foundation

import androidx.compose.foundation.onClick
import com.huanshankeji.compose.ui.Modifier

//@ExperimentalFoundationApi // `onClick` with a single parameter is likely to be always supported, so it does not require opt-in for now.
@ExperimentalFoundationApi
@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
actual fun Modifier.onClick(onClick: () -> Unit): Modifier =
    platformModify { onClick(onClick = onClick) }
