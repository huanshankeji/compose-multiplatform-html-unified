package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.*
import com.huanshankeji.compose.material3.slotEqSelectedModifier
import com.huanshankeji.compose.material3.toCommonIconToggleButtonAttrs
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull

private fun commonIconToggleButtonContent(
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
): @Composable MdIconButtonScope.() -> Unit = {
    uncheckedContent()
    checkedContent(slotEqSelectedModifier())
}


@Composable
actual fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
) =
    MdIconButton(
        enabled.isFalseOrNull(),
        toggle = true,
        selected = checked.isTrueOrNull(),
        attrs = modifier.toCommonIconToggleButtonAttrs(checked, onCheckedChange),
        content = commonIconToggleButtonContent(uncheckedContent, checkedContent)
    )


@Composable
actual fun FilledIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
) =
    MdFilledIconButton(
        enabled.isFalseOrNull(),
        toggle = true,
        selected = checked.isTrueOrNull(),
        attrs = modifier.toCommonIconToggleButtonAttrs(checked, onCheckedChange),
        content = commonIconToggleButtonContent(uncheckedContent, checkedContent)
    )

@Composable
actual fun FilledTonalIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
) =
    MdFilledTonalIconButton(
        enabled.isFalseOrNull(),
        toggle = true,
        selected = checked.isTrueOrNull(),
        attrs = modifier.toCommonIconToggleButtonAttrs(checked, onCheckedChange),
        content = commonIconToggleButtonContent(uncheckedContent, checkedContent)
    )

@Composable
actual fun OutlinedIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
) =
    MdOutlinedIconButton(
        enabled.isFalseOrNull(),
        toggle = true,
        selected = checked.isTrueOrNull(),
        attrs = modifier.toCommonIconToggleButtonAttrs(checked, onCheckedChange),
        content = commonIconToggleButtonContent(uncheckedContent, checkedContent)
    )
