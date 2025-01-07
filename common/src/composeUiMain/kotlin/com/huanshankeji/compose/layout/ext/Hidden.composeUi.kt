package com.huanshankeji.compose.layout.ext

import androidx.compose.ui.layout.layout
import com.huanshankeji.compose.ui.Modifier

// This approach comes from Gemini: https://g.co/gemini/share/27cfdc47ecff.
// also see: https://developer.android.com/develop/ui/compose/layouts/custom
actual fun Modifier.hidden(): Modifier =
    platformModify {
        layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(placeable.width, placeable.height) {}
        }
    }
