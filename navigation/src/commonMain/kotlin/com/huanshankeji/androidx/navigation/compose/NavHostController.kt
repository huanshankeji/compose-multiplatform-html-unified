package com.huanshankeji.androidx.navigation.compose


import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.*

@Composable
expect fun NavController.currentBackStackEntryAsState(): State<NavBackStackEntry?>

@Composable
expect fun rememberNavController(
    vararg navigators: Navigator<out NavDestination>
): NavHostController
