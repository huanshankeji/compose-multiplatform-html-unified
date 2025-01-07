package com.huanshankeji.browser

import kotlinx.browser.window

// TODO consider moving to "kotlin-common"

enum class Browser {
    Webkit, Mozilla
}

val browser = run {
    val userAgent = window.navigator.userAgent
    when {
        userAgent.contains("AppleWebKit") -> Browser.Webkit
        userAgent.contains("Mozilla") -> Browser.Mozilla
        else -> null
    }
}
