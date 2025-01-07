package com.huanshankeji.androidx.navigation

// It seems OK to not create a wrapper for this class and just use the one in `androidx.navigation`.
/*
expect class NavController {
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    @get:RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
     val currentBackStack: StateFlow<List<NavBackStackEntry>>

    @MainThread
    fun popBackStack(): Boolean
    @MainThread
    fun navigateUp(): Boolean

    @MainThread
    fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit)

    @MainThread
    //@JvmOverloads
    fun navigate(
        route: String,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    )

    val currentBackStackEntry: NavBackStackEntry?
}
*/
