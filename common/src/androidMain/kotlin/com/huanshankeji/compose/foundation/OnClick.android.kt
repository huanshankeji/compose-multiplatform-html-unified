package com.huanshankeji.compose.foundation

import androidx.compose.foundation.clickable
import com.huanshankeji.compose.ui.Modifier

@ExperimentalFoundationApi
actual fun Modifier.onClick(onClick: () -> Unit): Modifier =
    platformModify { clickable(onClick = onClick) }
