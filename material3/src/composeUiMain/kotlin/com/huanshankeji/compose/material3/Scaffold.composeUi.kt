package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.toCommonValue
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material3.FabPosition as PlatformFabPosition

fun FabPosition.toPlatformValue() =
    when (this) {
        FabPosition.Start -> PlatformFabPosition.Start
        FabPosition.Center -> PlatformFabPosition.Center
        FabPosition.End -> PlatformFabPosition.End
        FabPosition.EndOverlay -> PlatformFabPosition.EndOverlay
    }

@Composable
actual fun Scaffold(
    modifier: Modifier,
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    snackbarHost: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit,
    floatingActionButtonPosition: FabPosition,
    content: @Composable (PaddingValues) -> Unit,
) =
    androidx.compose.material3.Scaffold(
        modifier.platformModifier,
        topBar,
        bottomBar,
        snackbarHost,
        floatingActionButton,
        floatingActionButtonPosition.toPlatformValue(),
    ) { content(it.toCommonValue()) }
