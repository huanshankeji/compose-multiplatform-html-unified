package com.huanshankeji.compose.foundation.layout

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.kobweb.compose.ui.modifiers.imitateComposeUiLayout
import com.varabyte.kobweb.compose.foundation.layout.LayoutScopeMarker

@Composable
actual fun Row(
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment: Alignment.Vertical,
    content: @Composable RowScope.() -> Unit
) {
    AddKobwebComposeStyleSheet()
    com.varabyte.kobweb.compose.foundation.layout.Row(
        PlatformModifier
            .imitateComposeUiLayout()
            .stylesFrom(horizontalArrangement)
            .then(modifier.platformModifier),
        horizontalArrangement.platformValue,
        verticalAlignment.platformValue,
        content = content.toPlatformRowScopeContent()
    )
}


actual typealias PlatformRowScope = com.varabyte.kobweb.compose.foundation.layout.RowScope

@LayoutScopeMarker
actual interface RowScope {
    actual val platformValue: PlatformRowScope

    actual value class Impl(override val platformValue: PlatformRowScope) : RowScope

    @Stable
    actual fun Modifier.weight(
        @FloatRange(from = 0.0, fromInclusive = false)
        weight: Float
    ): Modifier =
        with(platformValue) { platformModify { flexBasis0().weight(weight) } }

    @Stable
    actual fun Modifier.align(alignment: Alignment.Vertical): Modifier =
        with(platformValue) { platformModify { align(alignment.platformValue) } }
}
