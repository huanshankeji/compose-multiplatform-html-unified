package com.huanshankeji.compose.ui.text.style

import org.jetbrains.compose.web.css.*

fun StyleScope.applyStyle(textOverflow: TextOverflow, softWrap: Boolean, maxLines: Int) {
    if (!softWrap) {
        property("white-space", "nowrap")
    }
    when (textOverflow) {
        TextOverflow.Clip -> {
            property("overflow", "hidden")
            property("text-overflow", "clip")
        }

        TextOverflow.Ellipsis -> {
            property("overflow", "hidden")
            property("text-overflow", "ellipsis")
        }

        TextOverflow.Visible -> {
            // no overflow clipping
        }
    }
    if (maxLines != Int.MAX_VALUE) {
        property("display", "-webkit-box")
        property("-webkit-line-clamp", "$maxLines")
        property("-webkit-box-orient", "vertical")
        property("overflow", "hidden")
    }
}
