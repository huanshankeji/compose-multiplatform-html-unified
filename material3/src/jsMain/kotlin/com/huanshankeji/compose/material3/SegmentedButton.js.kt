package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdOutlinedSegmentedButton
import com.huanshankeji.compose.html.material3.MdOutlinedSegmentedButtonSet
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

@MaterialWebLabsApi
@Composable
private fun CommonSegmentedButton(
    selected: Boolean,
    onClick: () -> Unit,
    defaultShapeArgs: SegmentedButtonDefaultShapeArgs, // not used here
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable (() -> Unit)?,
    label: String //@Composable () -> Unit
) {
    val hasIcon = (icon != null).isTrueOrNull()
    MdOutlinedSegmentedButton(
        enabled.isFalseOrNull(),
        selected.isTrueOrNull(),
        label,
        hasIcon,
        hasIcon,
        modifier.toAttrs {
            onClick { onClick() }
        }
    ) {
        icon?.let { iconContent ->
            Div({ slot(Slot.Icon) }) {
                iconContent()
            }
        }
        /*
        // For `label: @Composable () -> Unit` by Copilot. Might not work.
        // Render label in the default slot
        label()
        */
    }
}

actual class SingleChoiceSegmentedButtonRowScope(val elementScope: ElementScope<HTMLElement>) {
    @MaterialWebLabsApi
    @Composable
    actual fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        defaultShapeArgs: SegmentedButtonDefaultShapeArgs, // not used here
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: String //@Composable () -> Unit
    ) =
        CommonSegmentedButton(selected, onClick, defaultShapeArgs, modifier, enabled, icon, label)
}

actual class MultiChoiceSegmentedButtonRowScope(val elementScope: ElementScope<HTMLElement>) {
    @MaterialWebLabsApi
    @Composable
    actual fun SegmentedButton(
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit,
        defaultShapeArgs: SegmentedButtonDefaultShapeArgs, // not used here
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: String //@Composable () -> Unit
    ) =
        CommonSegmentedButton(
            checked, { onCheckedChange(!checked) },
            defaultShapeArgs, modifier, enabled, icon, label
        )
}
