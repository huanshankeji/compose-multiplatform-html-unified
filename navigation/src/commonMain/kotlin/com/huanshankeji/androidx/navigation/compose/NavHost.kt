package com.huanshankeji.androidx.navigation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun NavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    route: String? = null,
    builder: NavGraphBuilder.() -> Unit
)

@Composable
expect fun NavHost(
    navController: NavHostController,
    graph: NavGraph,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
)
