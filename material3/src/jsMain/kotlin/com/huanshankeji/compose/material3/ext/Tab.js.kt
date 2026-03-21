package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdPrimaryTab
import com.huanshankeji.compose.html.material3.MdSecondaryTab
import com.huanshankeji.compose.html.material3.MdTabScope.Slot
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.ui.toCommonModifier
import com.huanshankeji.compose.web.attributes.ext.ariaLabelledBy
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import com.varabyte.kobweb.compose.css.role
import com.varabyte.kobweb.compose.ui.attrsModifier

@Composable
actual fun PrimaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean, // not passed here, not sure whether it's supported by `md-parimary-tab`
    text: @Composable (() -> Unit)?,
    icon: @Composable ((Modifier) -> Unit)?,
    idJsDom: String?,
    ariaControlsJsDom: String?,
) =
    MdPrimaryTab(
        isTab = true,
        active = selected.isTrueOrNull(),
        hasIcon = icon?.let { true },
        iconOnly = (icon != null && text == null).isTrueOrNull(),
        //selected = selected.isTrueOrNull(),
        id = idJsDom,
        ariaControls = ariaControlsJsDom,
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
    enabled: Boolean, // not passed here, not sure whether it's supported by `md-secondary-tab`
    text: @Composable (() -> Unit)?,
    icon: @Composable ((Modifier) -> Unit)?,
    idJsDom: String?,
    ariaControlsJsDom: String?,
) =
    MdSecondaryTab(
        isTab = true,
        active = selected.isTrueOrNull(),
        hasIcon = icon?.let { true },
        iconOnly = (icon != null && text == null).isTrueOrNull(),
        //selected = selected.isTrueOrNull(),
        id = idJsDom,
        ariaControls = ariaControlsJsDom,
        attrs = modifier.toAttrs { onClick { onClick() } },
    ) {
        nullableContentWithSlot(icon, Slot.Icon)
        text?.invoke()
    }

@Composable
internal actual fun TabPanels(tabAndPanelArgss: List<TabAndPanelArgs>) {
    for (tabAndPanelArgs in tabAndPanelArgss) {
        // https://github.com/material-components/material-web/blob/main/docs/components/tabs.md#tab-panels
        tabAndPanelArgs.panelContent(PlatformModifier.attrsModifier {
            with(tabAndPanelArgs) {
                id(panelIdJsDom)
                role("tabpanel")
                ariaLabelledBy(tabIdJsDom)
            }
        }.toCommonModifier())
    }
}
