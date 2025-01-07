package com.huanshankeji.kobweb.compose.ui.modifiers

import com.varabyte.kobweb.compose.css.Height
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.css.height
import com.varabyte.kobweb.compose.css.width
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier

fun Modifier.size(width: Width, height: Height): Modifier =
    styleModifier {
        width(width)
        height(height)
    }

//TODO consider removing and inlining this
/**
 * Consider using [Modifier.imitateComposeUiLayout] instead when adding this for a layout.
 */
fun Modifier.sizeFitContent() =
    size(Width.FitContent, Height.FitContent)
