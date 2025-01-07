package com.huanshankeji.compose.material3.ext

import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.PlatformRowScope
import com.huanshankeji.compose.toContentWithoutModifier
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun NavigationBar(
    modifier: Modifier,
    content: @Composable NavigationBarScope.() -> Unit
) =
    androidx.compose.material3.NavigationBar(modifier.platformModifier) {
        NavigationBarScope(this).content()
    }

actual class NavigationBarScope(val rowScope: PlatformRowScope)

@Composable
actual fun NavigationBarScope.NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    selectedIcon: @Composable (Modifier) -> Unit,
    unselectedIcon: @Composable (Modifier) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    alwaysShowLabel: Boolean
) =
    rowScope.NavigationBarItem(
        selected,
        onClick,
        (if (selected) selectedIcon else unselectedIcon).toContentWithoutModifier(),
        modifier.platformModifier,
        enabled,
        label.toNullableTaglessText(),
        alwaysShowLabel
    )
