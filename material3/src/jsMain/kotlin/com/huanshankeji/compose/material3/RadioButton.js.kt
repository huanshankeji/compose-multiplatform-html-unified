package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdRadio
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.ext.disabled
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull

@Composable
actual fun RadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier,
    enabled: Boolean
) =
    MdRadio(
        checked = selected.isTrueOrNull(),
        attrs = modifier.toAttrs {
            disabled(enabled.isFalseOrNull())
            onClick?.let { callback -> 
                this.onClick { callback() } 
            }
        }
    )
