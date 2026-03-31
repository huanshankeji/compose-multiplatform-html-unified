package com.huanshankeji.compose.ui.text.font

actual typealias FontWeight = androidx.compose.ui.text.font.FontWeight

actual fun lerp(start: FontWeight, stop: FontWeight, fraction: Float): FontWeight =
    androidx.compose.ui.text.font.lerp(start, stop, fraction)
