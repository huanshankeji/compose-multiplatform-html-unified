package com.huanshankeji.compose.runtime

import androidx.compose.runtime.Composable

class DeferredComposableRunner {
    private var composables: MutableList<@Composable () -> Unit>? = null
    fun addComposable(composable: @Composable () -> Unit) {
        composables!!.add(composable)
    }

    /** Add the composable functions with the non-composable functions and then invoke them. */
    @Composable
    fun ComposableRun(content: () -> Unit) {
        composables = mutableListOf()
        content()
        for (composable in composables!!)
            composable()
        composables = null
    }
}
