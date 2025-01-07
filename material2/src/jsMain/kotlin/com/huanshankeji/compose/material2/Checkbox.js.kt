package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import dev.petuska.kmdc.checkbox.MDCCheckbox

// https://github.com/mpetuska/kmdc/blob/master/sandbox/src/jsMain/showcases/MDCCheckbox.kt
@Composable
actual fun Checkbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean
) =
    MDCCheckbox(checked, !enabled, attrs = modifier.toAttrs {
        onCheckedChange?.let { onInput { it(!checked) } }
    })
