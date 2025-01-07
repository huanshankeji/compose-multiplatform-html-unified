package com.huanshankeji.androidx.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
actual fun NavController.currentBackStackEntryAsState(): State<NavBackStackEntry?> =
    currentBackStackEntryAsState()

@Composable
actual fun rememberNavController(vararg navigators: Navigator<out NavDestination>): NavHostController =
    rememberNavController(*navigators)
