package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdCheckbox
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.ext.onInput
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull

@Composable
actual fun Checkbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean
) =
    MdCheckbox(
        checked.isTrueOrNull(),
        enabled.isFalseOrNull(),
        attrs = modifier.toAttrs {
            /*
            Use `onInput` here because it wraps an `input` element.
            Also see: https://stackoverflow.com/questions/58016503/click-vs-input-vs-change-for-checkboxes
             */

            //onCheckedChange?.let { onClick { it(!checked) } }
            onCheckedChange?.let { onInput { it(!checked) } }
        }
    )
