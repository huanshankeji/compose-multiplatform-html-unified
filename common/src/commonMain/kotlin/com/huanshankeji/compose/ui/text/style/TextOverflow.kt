package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Stable

/**
 * The following Compose UI `TextOverflow` values are not yet supported:
 * - `StartEllipsis` — no direct CSS equivalent; would need JavaScript intervention
 * - `MiddleEllipsis` — no direct CSS equivalent; would need JavaScript intervention
 */
expect value class TextOverflow internal constructor(internal val value: Int) {
    companion object {
        @Stable val Clip: TextOverflow
        @Stable val Ellipsis: TextOverflow
        @Stable val Visible: TextOverflow
        // StartEllipsis — no direct CSS equivalent; would need JavaScript intervention
        // MiddleEllipsis — no direct CSS equivalent; would need JavaScript intervention
    }
}
