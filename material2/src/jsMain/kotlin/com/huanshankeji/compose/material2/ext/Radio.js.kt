package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import dev.petuska.kmdc.form.field.MDCFormField
import dev.petuska.kmdc.radio.MDCRadio
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

// see: https://github.com/mpetuska/kmdc/blob/master/sandbox/src/jsMain/showcases/MDCRadio.kt

@Composable
actual fun RadioRow(selected: Boolean, label: String, onClick: () -> Unit, modifier: Modifier, enabled: Boolean) =
    Div {
        // `MDCRadio` adds 2 elements, which can cause the radio button and the label to not be on a same row if not wrapped in a `div`.
        MDCRadio(selected, disabled = !enabled, label = label, attrs = modifier.toAttrs {
            onClick { onClick() }
        })
    }

@Composable
actual fun RadioGroupRow(modifier: Modifier, content: @Composable () -> Unit) =
    MDCFormField(attrs = modifier.toAttrs()) {
        content()
    }

@Composable
actual fun RadioGroupColumn(modifier: Modifier, content: @Composable () -> Unit) =
    MDCFormField(attrs = modifier.toAttrs {
        style {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            alignItems(AlignItems.Start)
        }
    }) {
        //Column {
        content()
        //}
    }
