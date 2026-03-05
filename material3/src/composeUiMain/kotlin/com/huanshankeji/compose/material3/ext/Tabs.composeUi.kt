package com.huanshankeji.compose.material3.ext

import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ext.toNullableContentWithoutModifier
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun PrimaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    text: @Composable (() -> Unit)?,
    icon: @Composable ((Modifier) -> Unit)?,
    idJsDom: String?,
    ariaControlsJsDom: String?,
) =
    Tab(
        selected,
        onClick,
        modifier.platformModifier,
        enabled,
        text,
        icon.toNullableContentWithoutModifier()
    )

@Composable
actual fun SecondaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    text: @Composable (() -> Unit)?,
    icon: @Composable ((Modifier) -> Unit)?,
    idJsDom: String?,
    ariaControlsJsDom: String?,
) =
    Tab(
        selected,
        onClick,
        modifier.platformModifier,
        enabled,
        text,
        icon.toNullableContentWithoutModifier()
    )

@Composable
internal actual fun TabPanels(tabAndPanelArgss: List<TabAndPanelArgs>) {
    throw NotImplementedError()
}
