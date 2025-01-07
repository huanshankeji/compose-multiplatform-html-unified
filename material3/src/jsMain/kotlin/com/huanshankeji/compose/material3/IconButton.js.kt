package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.html.material3.*
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.ui.toCommonModifier
import com.huanshankeji.compose.web.attributes.ext.onInput
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import com.varabyte.kobweb.compose.ui.attrsModifier
import org.jetbrains.compose.web.attributes.AttrsScope
import org.w3c.dom.HTMLElement

internal fun Modifier.toCommonIconToggleButtonAttrs(
    checked: Boolean, onCheckedChange: (Boolean) -> Unit
): AttrsScope<HTMLElement>.() -> Unit =
    toAttrs {
        // note that `onInput` is used here
        onInput { onCheckedChange(!checked) }
    }

internal fun MdIconButtonScope.slotEqSelectedModifier() =
    PlatformModifier.attrsModifier { slotEqSelected() }.toCommonModifier()

private fun (@Composable () -> Unit).toCommonIconButtonContent(): @Composable MdIconButtonScope.() -> Unit = {
    Box { this@toCommonIconButtonContent() }
    Box(slotEqSelectedModifier()) { this@toCommonIconButtonContent() }
}

@Composable
actual fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    MdIconButton(
        enabled.isFalseOrNull(),
        attrs = modifier.toCommonButtonAttrs(onClick),
        content = content.toCommonIconButtonContent()
    )


@Composable
actual fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    MdIconButton(
        enabled.isFalseOrNull(),
        toggle = true,
        selected = checked.isTrueOrNull(),
        attrs = modifier.toCommonIconToggleButtonAttrs(checked, onCheckedChange),
        content = content.toCommonIconButtonContent()
    )

@Composable
actual fun FilledIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    MdFilledIconButton(
        enabled.isFalseOrNull(),
        attrs = modifier.toCommonButtonAttrs(onClick),
        content = content.toCommonIconButtonContent()
    )

@Composable
actual fun FilledIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    MdFilledIconButton(
        enabled.isFalseOrNull(),
        toggle = true,
        selected = checked.isTrueOrNull(),
        attrs = modifier.toCommonIconToggleButtonAttrs(checked, onCheckedChange),
        content = content.toCommonIconButtonContent()
    )

@Composable
actual fun FilledTonalIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    MdFilledTonalIconButton(
        enabled.isFalseOrNull(),
        attrs = modifier.toCommonButtonAttrs(onClick),
        content = content.toCommonIconButtonContent()
    )

@Composable
actual fun FilledTonalIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    MdFilledTonalIconButton(
        enabled.isFalseOrNull(),
        toggle = true,
        selected = checked.isTrueOrNull(),
        attrs = modifier.toCommonIconToggleButtonAttrs(checked, onCheckedChange),
        content = content.toCommonIconButtonContent()
    )

@Composable
actual fun OutlinedIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    MdOutlinedIconButton(
        enabled.isFalseOrNull(),
        attrs = modifier.toCommonButtonAttrs(onClick),
        content = content.toCommonIconButtonContent()
    )

@Composable
actual fun OutlinedIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    MdOutlinedIconButton(
        enabled.isFalseOrNull(),
        toggle = true,
        selected = checked.isTrueOrNull(),
        attrs = modifier.toCommonIconToggleButtonAttrs(checked, onCheckedChange),
        content = content.toCommonIconButtonContent()
    )
