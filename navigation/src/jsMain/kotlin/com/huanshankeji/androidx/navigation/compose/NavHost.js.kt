package com.huanshankeji.androidx.navigation.compose

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.*
import com.huanshankeji.androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.foundation.layout.fillMaxSize
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

// copied and adapted from "NavHost.kt" in `androidx.navigation.compose`


private class ComposeViewModelStoreOwner : ViewModelStoreOwner {
    override val viewModelStore: ViewModelStore = ViewModelStore()
    fun dispose() {
        viewModelStore.clear()
    }
}

@Composable
private fun rememberViewModelStoreOwner(): ViewModelStoreOwner {
    val viewModelStoreOwner = remember { ComposeViewModelStoreOwner() }
    DisposableEffect(viewModelStoreOwner) {
        onDispose { viewModelStoreOwner.dispose() }
    }
    return viewModelStoreOwner
}

@Composable
actual fun NavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier,
    contentAlignment: Alignment,
    route: String?,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController,
        remember(route, startDestination, builder) {
            navController.createGraph(startDestination, route, builder)
        },
        modifier,
        contentAlignment
    )
}

@Composable
actual fun NavHost(
    navController: NavHostController,
    graph: NavGraph,
    modifier: Modifier,
    contentAlignment: Alignment,
) {

    //val lifecycleOwner = LocalLifecycleOwner.current
    val viewModelStoreOwner = LocalViewModelStoreOwner.current ?: rememberViewModelStoreOwner()

    navController.setViewModelStore(viewModelStoreOwner.viewModelStore)

    // Then set the graph
    navController.graph = graph

    // Find the ComposeNavigator, returning early if it isn't found
    // (such as is the case when using TestNavHostController)
    val composeNavigator = navController.navigatorProvider.get<Navigator<out NavDestination>>(
        ComposeNavigator.NAME
    ) as? ComposeNavigator ?: return

    //val currentBackStack by composeNavigator.backStack.collectAsState()

//    BackHandler(currentBackStack.size > 1) {
//        navController.popBackStack()
//    }

    /*
    DisposableEffect(lifecycleOwner) {
        // Setup the navController with proper owners
        navController.setLifecycleOwner(lifecycleOwner)
        onDispose { }
    }
    */

    //val saveableStateHolder = rememberSaveableStateHolder()

    val allVisibleEntries by navController.visibleEntries.collectAsState()

    // Intercept back only when there's a destination to pop
    val visibleEntries by remember {
        derivedStateOf {
            allVisibleEntries.filter { entry ->
                entry.destination.navigatorName == ComposeNavigator.NAME
            }
        }
    }

    val backStackEntry: NavBackStackEntry? = visibleEntries.lastOrNull()

    if (backStackEntry != null) {
        // `fillMaxSize` is added here to make the Box align to the size of its parent
        // TODO consider adding a version of `NavHost` without `modifier` and `contentAlignment`
        // Originally it was `transition.AnimatedContent` here.
        Box(modifier.fillMaxSize(), contentAlignment) {
            val currentEntry = visibleEntries.lastOrNull { entry -> backStackEntry == entry }

            /*
            // while in the scope of the composable, we provide the navBackStackEntry as the
            // ViewModelStoreOwner and LifecycleOwner
            currentEntry?.LocalOwnersProvider(saveableStateHolder) {
                (currentEntry.destination as ComposeNavigator.Destination)
                    .content(currentEntry)
            }
            */
            currentEntry?.let {
                (it.destination as ComposeNavigator.Destination)
                    .content(currentEntry)
            }
        }

        DisposableEffect(true) {
            onDispose {
                visibleEntries.forEach { entry ->
                    composeNavigator.onTransitionComplete(entry)
                }
            }
        }
    }

    /*
    val dialogNavigator = navController.navigatorProvider.get<Navigator<out NavDestination>>(
        DialogNavigator.NAME
    ) as? DialogNavigator ?: return

    // Show any dialog destinations
    DialogHost(dialogNavigator)
    */
}
