package com.huanshankeji.compose.material3.labs

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdItem
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull

@MaterialWebLabsApi
@Composable
actual fun ListItem(
    modifier: Modifier,
    multiline: Boolean,
    content: @Composable () -> Unit
) =
    MdItem(
        multiline = multiline.isTrueOrNull(),
        attrs = modifier.toAttrs(),
        content = { content() }
    )
