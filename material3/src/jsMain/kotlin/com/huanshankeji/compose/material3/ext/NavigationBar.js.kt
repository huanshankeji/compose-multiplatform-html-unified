package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdNavigationBar
import com.huanshankeji.compose.html.material3.MdNavigationTab
import com.huanshankeji.compose.html.material3.MdNavigationTabScope
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.ui.toCommonModifier
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import com.varabyte.kobweb.compose.ui.attrsModifier
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLElement

@Composable
actual fun NavigationBar(
    modifier: Modifier,
    content: @Composable NavigationBarScope.() -> Unit
) =
    @OptIn(MaterialWebLabsApi::class)
    MdNavigationBar(attrs = modifier.toAttrs()) {
        NavigationBarScope(this).content()
    }

actual class NavigationBarScope(val elementScope: ElementScope<HTMLElement>)

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
    @OptIn(MaterialWebLabsApi::class)
    MdNavigationTab(
        enabled.isFalseOrNull(),
        selected.isTrueOrNull(),
        alwaysShowLabel.isFalseOrNull(),
        label,
        attrs = modifier.toAttrs {
            onClick { onClick() }
        }
    ) {
        selectedIcon(PlatformModifier.attrsModifier { slot(MdNavigationTabScope.Slot.ActiveIcon) }.toCommonModifier())
        unselectedIcon(
            PlatformModifier.attrsModifier { slot(MdNavigationTabScope.Slot.InactiveIcon) }.toCommonModifier()
        )
    }
