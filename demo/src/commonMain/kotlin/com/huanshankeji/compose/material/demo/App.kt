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
import com.huanshankeji.compose.material3.Button
import com.huanshankeji.compose.material3.ext.TaglessText
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

enum class Screen {
    Home, Common, /*Material2,*/ Material3, MaterialIcons
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
        composable(Screen.MaterialIcons.name) { MaterialIcons() }
    }
}

@Composable
fun Home(navController: NavHostController) {
    Box(Modifier.fillMaxSizeStretch(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
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
            Button({ navController.navigate(Screen.MaterialIcons.name) }) {
                TaglessText("Material Icons (Material 3)")
            }
        }
    }
}
