package com.huanshankeji.androidx.navigation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

actual fun NavGraphBuilder.composable(
    route: String,
    arguments: List<NamedNavArgument>,
    deepLinks: List<NavDeepLink>,
    content: @Composable (NavBackStackEntry) -> Unit
) =
    composable(route, arguments, deepLinks) { content(it) }
