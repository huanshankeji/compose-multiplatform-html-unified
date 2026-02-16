package com.huanshankeji.compose.material.demo

import com.huanshankeji.compose.ui.window.renderComposableInBodyWithViewModelStoreOwner

@JsModule("material-symbols/outlined.css")
private external object MaterialSymbolsOutlinedImport

fun main() {
    MaterialSymbolsOutlinedImport
    //renderComposableInBody { App() } // "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    renderComposableInBodyWithViewModelStoreOwner { App() }
}
