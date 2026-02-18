package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdFilledSelect
import com.huanshankeji.compose.html.material3.MdOutlinedSelect
import com.huanshankeji.compose.html.material3.MdSelectOption
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.ext.onInput
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import org.w3c.dom.HTMLElement

@Composable
actual fun FilledSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    options: @Composable () -> Unit
) =
    MdFilledSelect(
        disabled = enabled.isFalseOrNull(),
        label = label,
        attrs = modifier.toAttrs {
            onInput { event ->
                ((event.target as? HTMLElement)?.asDynamic()?.value as? String)?.let { newValue ->
                    onValueChange(newValue)
                }
            }
        }
    ) {
        options()
    }

@Composable
actual fun OutlinedSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    options: @Composable () -> Unit
) =
    MdOutlinedSelect(
        disabled = enabled.isFalseOrNull(),
        label = label,
        attrs = modifier.toAttrs {
            onInput { event ->
                ((event.target as? HTMLElement)?.asDynamic()?.value as? String)?.let { newValue ->
                    onValueChange(newValue)
                }
            }
        }
    ) {
        options()
    }

@Composable
actual fun SelectOption(
    value: String,
    text: String,
    modifier: Modifier
) =
    MdSelectOption(
        value = value,
        attrs = modifier.toAttrs()
    ) {
        org.jetbrains.compose.web.dom.Text(text)
    }
