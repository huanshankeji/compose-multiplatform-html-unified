package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design badge.
 *
 * Note: This uses experimental Material Web labs components on the JS platform.
 *
 * @see <a href="https://m3.material.io/components/badge/overview">Material Design badge</a>
 * @see androidx.compose.material3.Badge
 */
@Deprecated(
    "This component is not displayed correctly on JS DOM. " +
            "It seems to be displayed with absolute position. " +
            "See https://github.com/material-components/material-web/blob/516cbc02bf770b7c3c5c6b546f1e5d81939b9f23/labs/badge/internal/_badge.scss#L40 ."
)
@Composable
expect fun Badge(
    modifier: Modifier = Modifier,
    content: String,
)
