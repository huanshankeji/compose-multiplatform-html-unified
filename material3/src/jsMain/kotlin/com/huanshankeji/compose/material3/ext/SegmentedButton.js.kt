package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdOutlinedSegmentedButton
import com.huanshankeji.compose.html.material3.MdSegmentedButtonScope.Slot
import com.huanshankeji.compose.material3.MultiChoiceSegmentedButtonRowScope
import com.huanshankeji.compose.material3.SegmentedButtonDefaultShapeArgs
import com.huanshankeji.compose.material3.SingleChoiceSegmentedButtonRowScope
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Div

@MaterialWebLabsApi
@Composable
private fun CommonSegmentedButton(
    selected: Boolean,
    onClick: () -> Unit,
    defaultShapeArgs: SegmentedButtonDefaultShapeArgs, // not used on JS DOM
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable (() -> Unit)?,
    label: String //@Composable () -> Unit,
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

@MaterialWebLabsApi
@Composable
actual fun SingleChoiceSegmentedButtonRowScope.SegmentedButton(
    selected: Boolean,
    onClick: () -> Unit,
    defaultShapeArgs: SegmentedButtonDefaultShapeArgs, // not used on JS DOM
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable (() -> Unit)?,
    label: String //@Composable () -> Unit,
) =
    CommonSegmentedButton(selected, onClick, defaultShapeArgs, modifier, enabled, icon, label)

@MaterialWebLabsApi
@Composable
actual fun MultiChoiceSegmentedButtonRowScope.SegmentedButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    defaultShapeArgs: SegmentedButtonDefaultShapeArgs, // not used on JS DOM
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable (() -> Unit)?,
    label: String //@Composable () -> Unit,
) =
    CommonSegmentedButton(
        checked, { onCheckedChange(!checked) },
        defaultShapeArgs, modifier, enabled, icon, label,
    )
