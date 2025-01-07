package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import dev.petuska.kmdc.switch.MDCSwitch
import org.jetbrains.compose.web.attributes.disabled

// https://github.com/mpetuska/kmdc/blob/master/sandbox/src/jsMain/showcases/MDCSwitch.kt

@Composable
internal fun CommonSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    label: String?,
    modifier: Modifier,
    enabled: Boolean
) =
    MDCSwitch(checked, label, modifier.toAttrs {
        if (!enabled) disabled()
        onCheckedChange?.let { onClick { it(!checked) } }
    })

@Composable
actual fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean
) =
    CommonSwitch(checked, onCheckedChange, null, modifier, enabled)
