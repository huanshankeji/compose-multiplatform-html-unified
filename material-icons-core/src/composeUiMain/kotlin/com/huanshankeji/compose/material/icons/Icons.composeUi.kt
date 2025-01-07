package com.huanshankeji.compose.material.icons

import androidx.compose.ui.graphics.vector.ImageVector

actual typealias Icon = ImageVector

//typealias PlatformIcons = androidx.compose.material.icons.Icons // not working because nested objects are not members and can't be referenced with a typealias.
