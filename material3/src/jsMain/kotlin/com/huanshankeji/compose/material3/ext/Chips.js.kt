package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdAssistChip
import com.huanshankeji.compose.html.material3.MdChipScope.Slot
import com.huanshankeji.compose.html.material3.MdFilterChip
import com.huanshankeji.compose.html.material3.MdInputChip
import com.huanshankeji.compose.html.material3.MdSuggestionChip
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull

@Composable
actual fun AssistChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?
) =
    MdAssistChip(
        disabled = enabled.isFalseOrNull(),
        label = label,
        hasIcon = leadingIcon !== null,
        attrs = modifier.toAttrs {
            onClick { onClick() }
        }
    ) { nullableContentWithSlot(leadingIcon, Slot.Icon) }

@Composable
actual fun ElevatedAssistChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?
) =
    MdAssistChip(
        elevated = true,
        disabled = enabled.isFalseOrNull(),
        label = label,
        hasIcon = leadingIcon !== null,
        attrs = modifier.toAttrs {
            onClick { onClick() }
        }
    ) { nullableContentWithSlot(leadingIcon, Slot.Icon) }

@Composable
actual fun FilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?
) =
    MdFilterChip(
        disabled = enabled.isFalseOrNull(),
        selected = selected.isTrueOrNull(),
        label = label,
        hasIcon = leadingIcon !== null,
        attrs = modifier.toAttrs {
            onClick { onClick() }
        }
    ) { nullableContentWithSlot(leadingIcon, Slot.Icon) }

@Composable
actual fun ElevatedFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?
) =
    MdFilterChip(
        elevated = true,
        disabled = enabled.isFalseOrNull(),
        selected = selected.isTrueOrNull(),
        label = label,
        hasIcon = leadingIcon !== null,
        attrs = modifier.toAttrs {
            onClick { onClick() }
        }
    ) { nullableContentWithSlot(leadingIcon, Slot.Icon) }

@Composable
actual fun InputChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable ((Modifier) -> Unit)?,
    avatar: @Composable ((Modifier) -> Unit)?,
    showTrailingCloseIconComposeUi: Boolean,
    onRemove: (() -> Unit)?,
) =
    MdInputChip(
        avatar = avatar?.let { true },
        disabled = enabled.isFalseOrNull(),
        selected = selected.isTrueOrNull(),
        label = label,
        hasIcon = (leadingIcon ?: avatar)?.let { true },
        onRemove = onRemove,
        attrs = modifier.toAttrs {
            onClick { onClick() }
        }
    ) {
        // matches Compose UI's behavior where `avatar` shadows `leadingIcon`
        nullableContentWithSlot(avatar ?: leadingIcon, Slot.Icon)
    }

@Composable
actual fun SuggestionChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?
) =
    MdSuggestionChip(
        disabled = enabled.isFalseOrNull(),
        label = label,
        hasIcon = icon !== null,
        attrs = modifier.toAttrs {
            onClick { onClick() }
        }
    ) { nullableContentWithSlot(icon, Slot.Icon) }

@Composable
actual fun ElevatedSuggestionChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?
) =
    MdSuggestionChip(
        elevated = true,
        disabled = enabled.isFalseOrNull(),
        label = label,
        hasIcon = icon !== null,
        attrs = modifier.toAttrs {
            onClick { onClick() }
        }
    ) { nullableContentWithSlot(icon, Slot.Icon) }

