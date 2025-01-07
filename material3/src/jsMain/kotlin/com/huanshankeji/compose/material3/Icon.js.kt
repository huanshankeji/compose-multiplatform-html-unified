package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.contentDescription
import com.huanshankeji.compose.html.material3.MdIcon
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs

@Composable
actual fun Icon(
    icon: Icon,
    contentDescription: String?,
    modifier: Modifier
) =
    MdIcon(attrs = modifier.toAttrs {
        contentDescription(contentDescription)
    }, materialIconName = icon.name)
