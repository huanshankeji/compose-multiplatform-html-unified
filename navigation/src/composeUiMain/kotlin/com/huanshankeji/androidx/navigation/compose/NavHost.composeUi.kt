package com.huanshankeji.androidx.navigation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun NavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier,
    contentAlignment: Alignment,
    route: String?,
    builder: NavGraphBuilder.() -> Unit
) =
    NavHost(
        navController,
        startDestination,
        modifier.platformModifier,
        contentAlignment.platformAlignment,
        route,
        builder = builder
    )

@Composable
actual fun NavHost(
    navController: NavHostController,
    graph: NavGraph,
    modifier: Modifier,
    contentAlignment: Alignment
) =
    NavHost(navController, graph, modifier.platformModifier, contentAlignment.platformAlignment)
