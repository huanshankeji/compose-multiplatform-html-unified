package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.material3.PrimaryScrollableTabRow
import com.huanshankeji.compose.material3.PrimaryTabRow
import com.huanshankeji.compose.material3.SecondaryScrollableTabRow
import com.huanshankeji.compose.material3.SecondaryTabRow
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design tab.
 *
 * @see <a href="https://m3.material.io/components/tabs/overview">Material Design tabs</a>
 * @see androidx.compose.material3.Tab
 */
@Composable
expect fun PrimaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable ((Modifier) -> Unit)? = null,
    idJsDom: String? = null,
    ariaControlsJsDom: String? = null,
)

@Composable
expect fun SecondaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable ((Modifier) -> Unit)? = null,
    idJsDom: String? = null,
    ariaControlsJsDom: String? = null,
)

enum class PrimaryOrSecondary {
    Primary, Secondary
}

@ExperimentalApi
class TabAndPanelArgs(
    val tabIdJsDom: String,
    val tabArgs: TabArgs,
    val panelIdJsDom: String,
    val panelContent: @Composable (Modifier) -> Unit,
) {
    class TabArgs(
        val selected: Boolean,
        val onClick: () -> Unit,
        val modifier: Modifier = Modifier,
        val enabled: Boolean = true,
        val text: @Composable (() -> Unit)? = null,
        val icon: @Composable ((Modifier) -> Unit)? = null,
    )
}

/**
 * A tab row with panels. The selected panel is below the tab row.
 * See https://github.com/material-components/material-web/blob/main/docs/components/tabs.md#tab-panels.
 */
@Deprecated("This component needs to be refactored to depend on navigation on Compose UI. See https://developer.android.com/develop/ui/compose/components/tabs#example-tab-based for more details.")
@ExperimentalApi
@Composable
// temporarily made private
private fun TabRowWithPanels(
    primaryOrSecondary: PrimaryOrSecondary,
    isScrollable: Boolean = false,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabAndPanelArgss: List<TabAndPanelArgs>,
) {
    val tabRow: @Composable (selectedTabIndex: Int, modifier: Modifier, tabs: @Composable () -> Unit) -> Unit =
        when (primaryOrSecondary) {
            PrimaryOrSecondary.Primary -> if (isScrollable) ::PrimaryScrollableTabRow else ::PrimaryTabRow
            PrimaryOrSecondary.Secondary -> if (isScrollable) ::SecondaryScrollableTabRow else ::SecondaryTabRow
        }
    tabRow(selectedTabIndex, modifier) {
        for (tabAndPanelArgs in tabAndPanelArgss) {
            with(tabAndPanelArgs) {
                with(tabArgs) {
                    when (primaryOrSecondary) {
                        PrimaryOrSecondary.Primary -> PrimaryTab(
                            selected, onClick, modifier, enabled, text, icon, tabIdJsDom, panelIdJsDom
                        )

                        PrimaryOrSecondary.Secondary -> SecondaryTab(
                            selected, onClick, modifier, enabled, text, icon, tabIdJsDom, panelIdJsDom
                        )
                    }
                }
            }
        }
    }

    TabPanels(tabAndPanelArgss)
}

@Composable
internal expect fun TabPanels(tabAndPanelArgss: List<TabAndPanelArgs>)
