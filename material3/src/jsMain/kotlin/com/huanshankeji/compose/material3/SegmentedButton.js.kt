package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdOutlinedSegmentedButton
import com.huanshankeji.compose.html.material3.MdOutlinedSegmentedButtonSet
import com.huanshankeji.compose.html.material3.MdSegmentedButtonScope
import com.huanshankeji.compose.html.material3.MdSegmentedButtonScope.Slot
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLElement

@MaterialWebLabsApi
@Composable
actual fun SingleChoiceSegmentedButtonRow(
    modifier: Modifier,
    space: Dp?,
    content: @Composable SingleChoiceSegmentedButtonRowScope.() -> Unit
) =
    MdOutlinedSegmentedButtonSet(attrs = modifier.toAttrs()) {
        SingleChoiceSegmentedButtonRowScope(this).content()
    }

@MaterialWebLabsApi
@Composable
actual fun MultiChoiceSegmentedButtonRow(
    modifier: Modifier,
    space: Dp?,
    content: @Composable MultiChoiceSegmentedButtonRowScope.() -> Unit
) =
    MdOutlinedSegmentedButtonSet(multiselect = true, attrs = modifier.toAttrs()) {
        MultiChoiceSegmentedButtonRowScope(this).content()
    }

// TODO check their implementations and consider unifying their duplicate implementations

actual class SingleChoiceSegmentedButtonRowScope(val elementScope: ElementScope<HTMLElement>) {
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
                    slot(Slot.Icon)
                }) {
                    iconContent()
                }
            }
            // Render label in the default slot
            label()
        }
    }
}

actual class MultiChoiceSegmentedButtonRowScope(val elementScope: ElementScope<HTMLElement>) {
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
                    slot(Slot.Icon)
                }) {
                    iconContent()
                }
            }
            label()
        }
    }
}
