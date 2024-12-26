package com.huanshankeji.compose.material.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.huanshankeji.compose.foundation.VerticalScrollBox
import com.huanshankeji.compose.material.icons.Icons
import com.huanshankeji.compose.material.icons.filled.Done
import com.huanshankeji.compose.material.icons.filled.Menu
import com.huanshankeji.compose.material.icons.filled.Search
import com.huanshankeji.compose.material2.SnackbarHost
import com.huanshankeji.compose.material2.SnackbarHostState
import com.huanshankeji.compose.material2.Text
import com.huanshankeji.compose.material2.ext.TopAppBarScaffold
import com.huanshankeji.compose.material3.ext.TaglessText

@Composable
fun Material2(/*modifier: Modifier = Modifier*/) {
    val snackbarHostState = remember { SnackbarHostState() }
    // It seems the modifier can't be set on `TopAppBarScaffold` or a box wrapping it
    TopAppBarScaffold({
        Text("Compose Multiplatform HTML Unified demo")
    }, navigationIcon = {
        MaterialIconNavButton({}, icon = Icons.Default.Menu, contentDescription = "menu")
    }, actions = {
        MaterialIconActionButton({}, icon = Icons.Default.Search, contentDescription = "search")
        MaterialIconActionButton({}, icon = Icons.Default.Done, contentDescription = "done")
    }, bottomBar = {
        Text("Bottom bar") // The Material 2 bottom bar is not added so this is a placeholder.
    }, snackbarHost = {
        SnackbarHost(snackbarHostState)
    }, floatingActionButton = {
        Text("FAB") // The Material 2 FAB is not added so this is a placeholder.
    }) {
        VerticalScrollBox {
            TaglessText("Content")
        }
    }
}
