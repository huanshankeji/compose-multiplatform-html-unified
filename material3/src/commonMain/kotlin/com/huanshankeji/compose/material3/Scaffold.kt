package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.ui.Modifier

/*
https://developer.android.com/develop/ui/compose/components/scaffold
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-scaffold.html
 */

// copied and adapted from `FabPosition` in `androidx.compose.material3`
enum class FabPosition {
    Start, Center, End,

    EndOverlay
}

/**
 * Material Design layout.
 *
 * Scaffold implements the basic Material Design visual layout structure.
 *
 * This component provides API to put together several Material components to construct your screen,
 * by ensuring proper layout strategy for them and collecting necessary data so these components will work together correctly.
 *
 * @param modifier the [Modifier] to be applied to this scaffold
 * @param topBar top app bar of the screen, typically a [TopAppBar]
 * @param bottomBar bottom bar of the screen, typically a navigation bar
 * @param snackbarHost component to host [Snackbar]s that are pushed to be shown via [SnackbarHostState.showSnackbar],
 * typically a [SnackbarHost]
 * @param floatingActionButton Main action button of the screen, typically a [com.huanshankeji.compose.material3.FloatingActionButton]
 * @param floatingActionButtonPosition position of the FAB on the screen. See [FabPosition].
 * @param content content of the screen. The lambda receives a [PaddingValues] that should be applied to the content root
 * via [com.huanshankeji.compose.foundation.layout.padding] to properly offset top and bottom bars.
 * Conventional values are 64 DP for top and bottom bars if they exist and 0 for horizontal padding on Compose UI,
 * and 0 for all sides on JS DOM.
 * According to the corresponding doc for `androidx.compose.material3.Scaffold`, if the content is scrollable,
 * such values to be applied to "the child of the scroll, and not on the scroll itself".
 *
 * @see androidx.compose.material3.Scaffold
 */
@Composable
expect fun Scaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable (PaddingValues) -> Unit,
)
