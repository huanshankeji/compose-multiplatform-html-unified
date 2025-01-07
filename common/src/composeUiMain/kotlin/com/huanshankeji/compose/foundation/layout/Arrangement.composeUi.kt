package com.huanshankeji.compose.foundation.layout

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.layout.Arrangement as PlatformArrangement

@Immutable
actual object Arrangement {
    @Stable
    actual interface Horizontal {
        val platformValue: PlatformArrangement.Horizontal

        class Impl(override val platformValue: PlatformArrangement.Horizontal) : Horizontal
    }

    @Stable
    actual interface Vertical {
        val platformValue: PlatformArrangement.Vertical

        class Impl(override val platformValue: PlatformArrangement.Vertical) : Vertical
    }

    @Stable
    actual interface HorizontalOrVertical : Horizontal, Vertical {
        override val platformValue: PlatformArrangement.HorizontalOrVertical

        class Impl(override val platformValue: PlatformArrangement.HorizontalOrVertical) : HorizontalOrVertical
    }

    @Stable
    actual val Start: Horizontal = Horizontal.Impl(PlatformArrangement.Start)

    @Stable
    actual val End: Horizontal = Horizontal.Impl(PlatformArrangement.End)

    @Stable
    actual val Top: Vertical = Vertical.Impl(PlatformArrangement.Top)

    @Stable
    actual val Bottom: Vertical = Vertical.Impl(PlatformArrangement.Bottom)

    @Stable
    actual val Center: HorizontalOrVertical = HorizontalOrVertical.Impl(PlatformArrangement.Center)

    @Stable
    actual val SpaceEvenly: HorizontalOrVertical = HorizontalOrVertical.Impl(PlatformArrangement.SpaceEvenly)

    @Stable
    actual val SpaceBetween: HorizontalOrVertical = HorizontalOrVertical.Impl(PlatformArrangement.SpaceBetween)

    @Stable
    actual val SpaceAround: HorizontalOrVertical = HorizontalOrVertical.Impl(PlatformArrangement.SpaceAround)

    @Stable
    actual fun spacedBy(space: Dp): HorizontalOrVertical =
        HorizontalOrVertical.Impl(PlatformArrangement.spacedBy(space))
}