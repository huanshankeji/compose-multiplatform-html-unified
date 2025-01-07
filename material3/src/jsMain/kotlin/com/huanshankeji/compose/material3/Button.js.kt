package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.html.material3.*
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull

@Composable
private fun (@Composable (RowScope.() -> Unit)).toMdButtonScopeContent(): @Composable MdButtonScope.() -> Unit =
    // TODO consider adding the row styles/classes to the button and remove the wrapping `Row`
    { Row(content = this@toMdButtonScopeContent) }


@Composable
internal fun CommonButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    isTrailingIcon: Boolean = false,
    content: @Composable MdButtonScope.() -> Unit
) =
    MdFilledButton(
        disabled = enabled.isFalseOrNull(),
        trailingIcon = isTrailingIcon.isTrueOrNull(),
        attrs = modifier.toCommonButtonAttrs(onClick),
        content = content
    )

@Composable
actual fun Button(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    CommonButton(onClick, modifier, enabled, content = content.toMdButtonScopeContent())

@Composable
internal fun CommonElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    isTrailingIcon: Boolean = false,
    content: @Composable MdButtonScope.() -> Unit
) =
    MdElevatedButton(
        disabled = enabled.isFalseOrNull(),
        trailingIcon = isTrailingIcon.isTrueOrNull(),
        attrs = modifier.toCommonButtonAttrs(onClick),
        content = content
    )

@Composable
actual fun ElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    CommonElevatedButton(onClick, modifier, enabled, content = content.toMdButtonScopeContent())

@Composable
internal fun CommonFilledTonalButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    isTrailingIcon: Boolean = false,
    content: @Composable MdButtonScope.() -> Unit
) =
    MdFilledTonalButton(
        disabled = enabled.isFalseOrNull(),
        trailingIcon = isTrailingIcon.isTrueOrNull(),
        attrs = modifier.toCommonButtonAttrs(onClick),
        content = content
    )

@Composable
actual fun FilledTonalButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    CommonFilledTonalButton(onClick, modifier, enabled, content = content.toMdButtonScopeContent())

@Composable
internal fun CommonOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    isTrailingIcon: Boolean = false,
    content: @Composable MdButtonScope.() -> Unit
) =
    MdOutlinedButton(
        disabled = enabled.isFalseOrNull(),
        trailingIcon = isTrailingIcon.isTrueOrNull(),
        attrs = modifier.toCommonButtonAttrs(onClick),
        content = content
    )

@Composable
actual fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    CommonOutlinedButton(onClick, modifier, enabled, content = content.toMdButtonScopeContent())

@Composable
internal fun CommonTextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    isTrailingIcon: Boolean = false,
    content: @Composable MdButtonScope.() -> Unit
) =
    MdTextButton(
        disabled = enabled.isFalseOrNull(),
        trailingIcon = isTrailingIcon.isTrueOrNull(),
        attrs = modifier.toCommonButtonAttrs(onClick),
        content = content
    )

@Composable
actual fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    CommonTextButton(onClick, modifier, enabled, content = content.toMdButtonScopeContent())
