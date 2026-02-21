package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.html.material3.MdPrimaryTab
import com.huanshankeji.compose.html.material3.MdTabs
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Div

@Composable
actual fun TabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable () -> Unit
) =
    MdTabs(
        attrs = modifier.toAttrs()
    ) {
        tabs()
    }

@Composable
actual fun Tab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    text: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?
) =
    MdPrimaryTab(
        active = selected.isTrueOrNull(),
        attrs = modifier.toAttrs {
            this.onClick { onClick() }
        }
    ) {
        icon?.let { iconContent ->
            Div({
                slotEqIcon()
            }) {
                iconContent()
            }
        }
        text?.let { textContent -> textContent() }
    }
