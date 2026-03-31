package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Stable
import com.huanshankeji.browser.Browser
import com.huanshankeji.browser.browser
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.web.css.height
import com.huanshankeji.compose.web.css.width
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.css.StyleScope

private const val CSS_STRETCH_VALUE = "stretch" // This does not work on Chrome.
private const val CSS_WEBKIT_STRETCH_VALUE = "-webkit-fill-available" // This actually works on FireFox too as tested.
private const val CSS_MOZ_STRETCH_VALUE = "-moz-available"
val cssWidthStretchValueBrowserDependent =
    when (browser) {
        Browser.Webkit -> CSS_WEBKIT_STRETCH_VALUE
        Browser.Mozilla -> CSS_MOZ_STRETCH_VALUE
        null -> ""
    }

val cssHeightStretchValueBrowserDependent =
    when (browser) {
        Browser.Webkit -> CSS_WEBKIT_STRETCH_VALUE
        Browser.Mozilla -> "100%" // Setting `CSS_MOZ_STRETCH_VALUE` for `height` seems to be not available on Firefox. See https://developer.mozilla.org/en-US/docs/Web/CSS/height#browser_compatibility.
        null -> ""
    }

fun StyleScope.widthStretch() =
    width(cssWidthStretchValueBrowserDependent)

fun StyleScope.heightStretch() =
    height(cssHeightStretchValueBrowserDependent)

@Stable
actual fun Modifier.fillMaxWidthStretch(): Modifier =
    platformModify { styleModifier { widthStretch() } }

@Stable
actual fun Modifier.fillMaxHeightStretch(): Modifier =
    platformModify { styleModifier { heightStretch() } }

@Stable
actual fun Modifier.fillMaxSizeStretch(): Modifier =
    platformModify {
        styleModifier {
            widthStretch()
            heightStretch()
        }
    }
