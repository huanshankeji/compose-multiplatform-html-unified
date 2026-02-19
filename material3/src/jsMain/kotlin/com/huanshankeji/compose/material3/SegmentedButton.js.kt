package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdOutlinedSegmentedButton
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Div

@MaterialWebLabsApi
@Composable
actual fun SingleChoiceSegmentedButtonRow(
    modifier: Modifier,
    content: @Composable SingleChoiceSegmentedButtonRowScope.() -> Unit
) =
    Row(modifier) {
        SingleChoiceSegmentedButtonRowScope().content()
    }

@MaterialWebLabsApi
@Composable
actual fun MultiChoiceSegmentedButtonRow(
    modifier: Modifier,
    content: @Composable MultiChoiceSegmentedButtonRowScope.() -> Unit
) =
    Row(modifier) {
        MultiChoiceSegmentedButtonRowScope().content()
    }

actual class SingleChoiceSegmentedButtonRowScope {
    @MaterialWebLabsApi
    @Composable
    actual fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: @Composable () -> Unit
    ) {
        MdOutlinedSegmentedButton(
            disabled = enabled.isFalseOrNull(),
            selected = selected.isTrueOrNull(),
            hasIcon = (icon != null).isTrueOrNull(),
            attrs = modifier.toAttrs {
                this.onClick { onClick() }
            }
        ) {
            icon?.let { iconContent ->
                Div({
                    slotEqIcon()
                }) {
                    iconContent()
                }
            }
            // Render label in the default slot
            label()
        }
    }
}

actual class MultiChoiceSegmentedButtonRowScope {
    @MaterialWebLabsApi
    @Composable
    actual fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: @Composable () -> Unit
    ) {
        MdOutlinedSegmentedButton(
            disabled = enabled.isFalseOrNull(),
            selected = selected.isTrueOrNull(),
            hasIcon = (icon != null).isTrueOrNull(),
            attrs = modifier.toAttrs {
                this.onClick { onClick() }
            }
        ) {
            icon?.let { iconContent ->
                Div({
                    slotEqIcon()
                }) {
                    iconContent()
                }
            }
            label()
        }
    }
}
