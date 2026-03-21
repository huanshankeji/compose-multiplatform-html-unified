package com.huanshankeji.compose.foundation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun rememberScrollState(initial: Int): ScrollState =
    rememberScrollState(initial)

actual typealias ScrollState = androidx.compose.foundation.ScrollState

actual fun Modifier.verticalScroll(state: ScrollState): Modifier =
    platformModify { verticalScroll(state) }

actual fun Modifier.horizontalScroll(state: ScrollState): Modifier =
    platformModify { horizontalScroll(state) }
