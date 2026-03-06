package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdDivider
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs

@Composable
actual fun HorizontalDivider(
    modifier: Modifier,
) =
    MdDivider(
        attrs = modifier.toAttrs(),
    )
