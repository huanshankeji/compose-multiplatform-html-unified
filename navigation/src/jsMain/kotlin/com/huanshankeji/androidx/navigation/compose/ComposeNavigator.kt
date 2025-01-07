package com.huanshankeji.androidx.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.huanshankeji.androidx.navigation.compose.ComposeNavigator.Destination

// copied and adapted from "ComposeNavigator.kt" in `androidx.navigation.compose`

/*actual*/ class ComposeNavigator : Navigator<Destination>(NAME) {
    internal /*actual*/ val transitionsInProgress get() = state.transitionsInProgress

    /*actual*/ val backStack get() = state.backStack

    internal /*actual*/ val isPop = mutableStateOf(false)

    override fun navigate(
        entries: List<NavBackStackEntry>,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ) {
        entries.forEach { entry ->
            state.pushWithTransition(entry)
        }
        isPop.value = false
    }

    override fun createDestination(): Destination {
        return Destination(this) { }
    }

    override fun popBackStack(popUpTo: NavBackStackEntry, savedState: Boolean) {
        state.popWithTransition(popUpTo, savedState)
        isPop.value = true
    }

    /*actual*/ fun prepareForTransition(entry: NavBackStackEntry) {
        state.prepareForTransition(entry)
    }

    /*actual*/ fun onTransitionComplete(entry: NavBackStackEntry) {
        state.markTransitionComplete(entry)
    }

    /*actual*/ class Destination /*actual constructor*/(
        navigator: ComposeNavigator,
        internal /*actual*/ val content:
        @Composable /*AnimatedContentScope.*/(/*@JvmSuppressWildcards*/ NavBackStackEntry) -> Unit
    ) : NavDestination(navigator)

    internal /*actual*/ companion object {
        internal /*actual*/ const val NAME = "composable"
    }
}
