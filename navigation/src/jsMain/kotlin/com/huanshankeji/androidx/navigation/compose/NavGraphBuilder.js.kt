package com.huanshankeji.androidx.navigation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.*

// copied and adapted from "NavGraphBuilder.kt" in `androidx.navigation.compose`

actual fun NavGraphBuilder.composable(
    route: String,
    arguments: List<NamedNavArgument>,
    deepLinks: List<NavDeepLink>,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        ComposeNavigator.Destination(
            provider[ComposeNavigator.NAME],
            content
        ).apply {
            this.route = route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}
