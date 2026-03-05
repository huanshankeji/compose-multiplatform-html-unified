package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdPrimaryTab
import com.huanshankeji.compose.html.material3.MdSecondaryTab
import com.huanshankeji.compose.html.material3.MdTabScope.Slot
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull

@Composable
actual fun PrimaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    text: @Composable (() -> Unit)?,
    icon: @Composable ((Modifier) -> Unit)?
) =
    MdPrimaryTab(
        isTab = true,
        active = selected.isTrueOrNull(),
        hasIcon = icon?.let { true },
        iconOnly = (icon != null && text == null).isTrueOrNull(),
        //selected = selected.isTrueOrNull(),
        attrs = modifier.toAttrs { onClick { onClick() } },
    ) {
        nullableContentWithSlot(icon, Slot.Icon)
        text?.invoke()
    }

@Composable
actual fun SecondaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    text: @Composable (() -> Unit)?,
    icon: @Composable ((Modifier) -> Unit)?
) =
    MdSecondaryTab(
        isTab = true,
        active = selected.isTrueOrNull(),
        hasIcon = icon?.let { true },
        iconOnly = (icon != null && text == null).isTrueOrNull(),
        //selected = selected.isTrueOrNull(),
        attrs = modifier.toAttrs { onClick { onClick() } },
    ) {
        nullableContentWithSlot(icon, Slot.Icon)
        text?.invoke()
    }
