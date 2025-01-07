package com.huanshankeji.compose.foundation.layout

import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
import com.varabyte.kobweb.compose.css.Height
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.width

@Stable
actual fun Modifier.width(intrinsicSize: IntrinsicSize): Modifier =
    platformModify {
        width(
            when (intrinsicSize) {
                IntrinsicSize.Min -> Width.MinContent
                IntrinsicSize.Max -> Width.MaxContent
            }
        )
    }

@Stable
actual fun Modifier.height(intrinsicSize: IntrinsicSize): Modifier =
    platformModify {
        height(
            when (intrinsicSize) {
                IntrinsicSize.Min -> Height.MinContent
                IntrinsicSize.Max -> Height.MaxContent
            }
        )
    }
