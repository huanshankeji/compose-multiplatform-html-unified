package com.huanshankeji.compose.material2.icons

import com.huanshankeji.compose.material.icons.Icon
import dev.petuska.kmdcx.icons.MDCIcon
import dev.petuska.kmdcx.icons.MDCIconType
import dev.petuska.kmdcx.icons.mdcIcon
import org.jetbrains.compose.web.attributes.AttrsScope

val mdcIconMap = MDCIcon.entries.associateBy { it.type }

fun Icon.toMDCIcon() =
    mdcIconMap.getValue(name)


// Copied and adapted from https://github.com/mpetuska/kmdc/blob/fe1c35086e05d66b66c1deff5dac23dc4dcffbcd/kmdcx/kmdcx-icons/src/jsMain/kotlin/MDCIcon.kt#L16-L17
// for places where this style should be but is not imported
@JsNonModule
@JsModule("material-icons/iconfont/material-icons.css")
internal external val Style: dynamic

//@MDCAttrsDsl
fun AttrsScope<*>.mdcIconWithStyle(type: MDCIconType = MDCIconType.Filled) {
    Style
    mdcIcon(type)
}
