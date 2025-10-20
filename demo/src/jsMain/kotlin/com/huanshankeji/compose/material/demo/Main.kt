package com.huanshankeji.compose.material.demo

import com.huanshankeji.compose.html.material3.require
import com.huanshankeji.compose.ui.window.renderComposableInBodyWithViewModelStoreOwner

fun main() {
    require("material-symbols/outlined.css")
    //renderComposableInBody { App() } // "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    renderComposableInBodyWithViewModelStoreOwner { App() }
}
