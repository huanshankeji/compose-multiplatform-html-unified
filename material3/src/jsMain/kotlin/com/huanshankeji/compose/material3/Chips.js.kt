package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.html.material3.MdAssistChip
import com.huanshankeji.compose.html.material3.MdChipScope
import com.huanshankeji.compose.html.material3.MdChipScope.Slot
import com.huanshankeji.compose.html.material3.MdFilterChip
import com.huanshankeji.compose.html.material3.MdInputChip
import com.huanshankeji.compose.html.material3.MdSuggestionChip
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Div

@Composable
actual fun AssistChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable (() -> Unit)?
) =
    MdAssistChip(
        disabled = enabled.isFalseOrNull(),
        attrs = modifier.toAttrs {
            this.onClick { onClick() }
        }
    ) {
        leadingIcon?.let { icon ->
            Div({
                slot(Slot.Icon)
            }) {
                icon()
            }
        }
        label()
    }

@Composable
actual fun FilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable (() -> Unit)?
) =
    MdFilterChip(
        disabled = enabled.isFalseOrNull(),
        selected = selected.isTrueOrNull(),
        attrs = modifier.toAttrs {
            this.onClick { onClick() }
        }
    ) {
        leadingIcon?.let { icon ->
            Div({
                slot(Slot.Icon)
            }) {
                icon()
            }
        }
        label()
    }

@Composable
actual fun InputChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable (() -> Unit)?
) =
    MdInputChip(
        disabled = enabled.isFalseOrNull(),
        selected = selected.isTrueOrNull(),
        attrs = modifier.toAttrs {
            this.onClick { onClick() }
        }
    ) {
        leadingIcon?.let { icon ->
            Div({
                slot(Slot.Icon)
            }) {
                icon()
            }
        }
        label()
    }

@Composable
actual fun SuggestionChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable (() -> Unit)?
) =
    MdSuggestionChip(
        disabled = enabled.isFalseOrNull(),
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
