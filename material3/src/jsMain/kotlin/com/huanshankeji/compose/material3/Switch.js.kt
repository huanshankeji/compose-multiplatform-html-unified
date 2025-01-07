package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdSwitch
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.ext.onInput
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull

@Composable
actual fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean
) =
    MdSwitch(
        enabled.isFalseOrNull(),
        checked.isTrueOrNull(),
        attrs = modifier.toAttrs {
            onCheckedChange?.let { onInput { onCheckedChange(!checked) } }
        })
