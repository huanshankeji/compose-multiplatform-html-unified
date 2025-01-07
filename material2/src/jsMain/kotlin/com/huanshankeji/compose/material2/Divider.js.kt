package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Hr

// https://developer.mozilla.org/en-US/docs/Web/HTML/Element/hr
// see: https://github.com/varabyte/kobweb/blob/main/frontend/silk-widgets/src/jsMain/kotlin/com/varabyte/kobweb/silk/components/layout/Divider.kt
// see: https://github.com/mpetuska/kmdc/blob/master/katalog/katalog-runtime/src/jsMain/kotlin/layout/Divider.kt
@Composable
actual fun Divider(modifier: Modifier) =
    Hr(modifier.toAttrs())
