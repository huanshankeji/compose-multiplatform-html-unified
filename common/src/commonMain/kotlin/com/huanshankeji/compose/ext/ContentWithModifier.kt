package com.huanshankeji.compose.ext

/*
// This API seems to reduce the readability of code
fun <Scope> (@Composable () -> Unit).toContentWithoutScope(): @Composable Scope.() -> Unit =
    { this@toContentWithoutScope() }
*/

//typealias ContentWithModifier = @Composable (Modifier) -> Unit
//typealias NullableContentWithModifier = ContentWithModifier?
