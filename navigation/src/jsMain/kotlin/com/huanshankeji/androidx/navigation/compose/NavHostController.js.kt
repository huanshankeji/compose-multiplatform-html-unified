package com.huanshankeji.androidx.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.*

// copied and adapted from "NavHostController.jb.kt" in `androidx.navigation.compose`

@Composable
actual fun NavController.currentBackStackEntryAsState(): State<NavBackStackEntry?> {
    return currentBackStackEntryFlow.collectAsState(null)
}

@Composable
actual fun rememberNavController(
    vararg navigators: Navigator<out NavDestination>
): NavHostController {
    // `rememberSaveable` seems not needed here
    return remember {
        createNavController()
    }.apply {
        for (navigator in navigators) {
            navigatorProvider.addNavigator(navigator)
        }
    }
}

private fun createNavController() =
    NavHostController().apply {
        //navigatorProvider.addNavigator(ComposeNavGraphNavigator(navigatorProvider))
        navigatorProvider.addNavigator(ComposeNavigator())
        //navigatorProvider.addNavigator(DialogNavigator())
    }
