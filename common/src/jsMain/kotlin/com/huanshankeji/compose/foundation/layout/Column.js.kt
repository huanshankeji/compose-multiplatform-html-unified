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
actual fun Column(
    modifier: Modifier,
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    content: @Composable ColumnScope.() -> Unit
) {
    AddKobwebComposeStyleSheet()
    com.varabyte.kobweb.compose.foundation.layout.Column(
        PlatformModifier
            .imitateComposeUiLayout() // "fit-content" is added to make it consistent with the `androidx` one
            .stylesFrom(verticalArrangement)
            .then(modifier.platformModifier),
        verticalArrangement.platformValue,
        horizontalAlignment.platformValue,
        content = content.toCommonColumnScopeContent()
    )
}


actual typealias PlatformColumnScope = com.varabyte.kobweb.compose.foundation.layout.ColumnScope

@LayoutScopeMarker
actual interface ColumnScope {
    actual val platformValue: PlatformColumnScope

    actual value class Impl(override val platformValue: PlatformColumnScope) : ColumnScope

    @Stable
    actual fun Modifier.weight(
        @FloatRange(from = 0.0, fromInclusive = false)
        weight: Float
    ): Modifier =
        // `flex-basis: 0` added to be consistent with the Compose UI behavior
        with(platformValue) { platformModify { flexBasis0().weight(weight) } }

    @Stable
    actual fun Modifier.align(alignment: Alignment.Horizontal): Modifier =
        with(platformValue) { platformModify { align(alignment.platformValue) } }
}
