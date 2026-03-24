package com.huanshankeji.compose.material.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.huanshankeji.androidx.navigation.compose.NavHost
import com.huanshankeji.androidx.navigation.compose.composable
import com.huanshankeji.androidx.navigation.compose.rememberNavController
import com.huanshankeji.compose.foundation.layout.Arrangement
import com.huanshankeji.compose.foundation.layout.Box
import com.huanshankeji.compose.foundation.layout.Column
import com.huanshankeji.compose.foundation.layout.ext.fillMaxSizeStretch
import com.huanshankeji.compose.foundation.layout.ext.innerPadding
import com.huanshankeji.compose.foundation.layout.ext.outerPadding
import com.huanshankeji.compose.material3.Button
import com.huanshankeji.compose.material3.ext.TaglessText
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

internal enum class Selection {
    A, B, C;

    fun displayText(): String = buildString {
        append(name)
        repeat(7) { append(name.lowercase()) }
    }
}

val listSize = 160.dp

fun Modifier.outerContentPadding() = outerPadding(16.dp)
fun Modifier.innerContentPadding() = innerPadding(16.dp)
val contentPaddingModifier = Modifier.outerContentPadding()

enum class Screen {
    Home, Common, /*Material2,*/ Material3
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController, Screen.Home.name) {
        composable(Screen.Home.name) { Home(navController) }
        //fun subDemoModifier()
        composable(Screen.Common.name) { Common() }
        //composable(Screen.Material2.name) { Material2() }
        composable(Screen.Material3.name) { Material3() }
    }
}

@Composable
fun Home(navController: NavHostController) {
    Box(Modifier.fillMaxSizeStretch(), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Button({ navController.navigate(Screen.Common.name) }) {
                TaglessText("Common (Foundation and UI)")
            }
            /*
            Button({ navController.navigate(Screen.Material2.name) }) {
                TaglessText("Material 2")
            }
            */
            Button({ navController.navigate(Screen.Material3.name) }) {
                TaglessText("Material 3")
            }
        }
    }
}
