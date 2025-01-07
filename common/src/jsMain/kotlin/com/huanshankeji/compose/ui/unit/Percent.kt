package com.huanshankeji.compose.ui.unit

import org.jetbrains.compose.web.css.percent

fun Float.toPercent() =
    (this * 100).percent
