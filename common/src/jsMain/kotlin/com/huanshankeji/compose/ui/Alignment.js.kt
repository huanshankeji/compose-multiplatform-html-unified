package com.huanshankeji.compose.ui

import androidx.compose.runtime.Stable
import com.varabyte.kobweb.compose.ui.Alignment as PlatformAlignment

@Stable
actual interface Alignment {
    val platformValue: PlatformAlignment

    @Stable
    value class Impl(override val platformValue: PlatformAlignment) : Alignment

    @Stable
    actual interface Horizontal {
        val platformValue: PlatformAlignment.Horizontal

        @Stable
        value class Impl(override val platformValue: PlatformAlignment.Horizontal) : Horizontal
    }

    @Stable
    actual interface Vertical {
        val platformValue: PlatformAlignment.Vertical

        @Stable
        value class Impl(override val platformValue: PlatformAlignment.Vertical) : Vertical
    }

    actual companion object {
        @Stable
        actual val TopStart: Alignment = Impl(PlatformAlignment.TopStart)

        @Stable
        actual val TopCenter: Alignment = Impl(PlatformAlignment.TopCenter)

        @Stable
        actual val TopEnd: Alignment = Impl(PlatformAlignment.TopEnd)

        @Stable
        actual val CenterStart: Alignment = Impl(PlatformAlignment.CenterStart)

        @Stable
        actual val Center: Alignment = Impl(PlatformAlignment.Center)

        @Stable
        actual val CenterEnd: Alignment = Impl(PlatformAlignment.CenterEnd)

        @Stable
        actual val BottomStart: Alignment = Impl(PlatformAlignment.BottomStart)

        @Stable
        actual val BottomCenter: Alignment = Impl(PlatformAlignment.BottomCenter)

        @Stable
        actual val BottomEnd: Alignment = Impl(PlatformAlignment.BottomEnd)

        @Stable
        actual val Top: Vertical = Vertical.Impl(PlatformAlignment.Top)

        @Stable
        actual val CenterVertically: Vertical = Vertical.Impl(PlatformAlignment.CenterVertically)

        @Stable
        actual val Bottom: Vertical = Vertical.Impl(PlatformAlignment.Bottom)

        @Stable
        actual val Start: Horizontal = Horizontal.Impl(PlatformAlignment.Start)

        @Stable
        actual val CenterHorizontally: Horizontal = Horizontal.Impl(PlatformAlignment.CenterHorizontally)

        @Stable
        actual val End: Horizontal = Horizontal.Impl(PlatformAlignment.End)
    }
}
