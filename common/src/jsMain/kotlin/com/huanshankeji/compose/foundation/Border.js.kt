package com.huanshankeji.compose.foundation

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.ext.outerBorder
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Brush
import com.huanshankeji.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.css.BoxSizing
import com.varabyte.kobweb.compose.ui.modifiers.boxSizing
import org.jetbrains.compose.web.css.LineStyle

@Stable
actual fun Modifier.border(border: BorderStroke): Modifier =
    platformModify {
        // TODO not working, see the SO question comment
        // see: https://stackoverflow.com/a/9601431/5082913
        boxSizing(BoxSizing.BorderBox)
        /*
        styleModifier {
            boxSizing(BoxSizing.BorderBox)
            property("-moz-box-sizing", BoxSizing.BorderBox.toString())
            property("-webkit-box-sizing", BoxSizing.BorderBox.toString())
        }
        */
    }
        .outerBorder(border)

@Stable
actual fun Modifier.border(width: Dp, color: Color): Modifier =
    border(BorderStroke(width, Brush(LineStyle.Solid /* `null` produces no border */, color)))

/*
@Stable
actual fun Modifier.border(width: Dp, brush: Brush): Modifier =
    border(BorderStroke(width, brush))
*/
