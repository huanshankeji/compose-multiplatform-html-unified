package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdTabs
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.attributes.AttrsScope

@Composable
private fun CommonTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    finalHandler: (AttrsScope<*>.() -> Unit)? = null,
    tabs: @Composable (() -> Unit),
) {
    MdTabs(
        activeTabIndex = selectedTabIndex,
        attrs = modifier.toAttrs(finalHandler)
    ) {
        tabs()
    }
}

@Composable
actual fun PrimaryTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable () -> Unit,
) =
    CommonTabRow(selectedTabIndex, modifier, tabs = tabs)

@Composable
private fun CommonScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable (() -> Unit)
) {
    CommonTabRow(selectedTabIndex, modifier, {
        // from https://material-web.dev/components/tabs/stories/
        classes("scrolling")
    }, tabs)
}

@Composable
actual fun SecondaryTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable (() -> Unit),
) =
    CommonTabRow(selectedTabIndex, modifier, tabs = tabs)

@Composable
actual fun PrimaryScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable (() -> Unit),
) =
    CommonScrollableTabRow(selectedTabIndex, modifier, tabs)


@Composable
actual fun SecondaryScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable (() -> Unit),
) =
    CommonScrollableTabRow(selectedTabIndex, modifier, tabs)
