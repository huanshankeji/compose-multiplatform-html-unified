package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun NavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable NavigationBarScope.() -> Unit
)

expect class NavigationBarScope

@Composable
expect fun NavigationBarScope.NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    selectedIcon: @Composable (Modifier) -> Unit,
    unselectedIcon: @Composable (Modifier) -> Unit = selectedIcon,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    alwaysShowLabel: Boolean = true,
)

@Composable
fun NavigationBarScope.NavigationBarItemWithMaterialIcons(
    selected: Boolean,
    onClick: () -> Unit,
    selectedIcon: Icon,
    unselectedIcon: Icon = selectedIcon,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    alwaysShowLabel: Boolean = true,
) =
    NavigationBarItem(
        selected,
        onClick,
        selectedIcon.toContentWithModifier(),
        unselectedIcon.toContentWithModifier(),
        modifier,
        enabled,
        label,
        alwaysShowLabel
    )
