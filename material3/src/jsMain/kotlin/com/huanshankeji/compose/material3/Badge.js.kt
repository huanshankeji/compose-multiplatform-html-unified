package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdBadge
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs

@MaterialWebLabsApi
@Composable
actual fun Badge(
    modifier: Modifier,
    content: @Composable (() -> Unit)?
) =
    MdBadge(
        attrs = modifier.toAttrs(),
        content = content?.let { { it() } }
    )
