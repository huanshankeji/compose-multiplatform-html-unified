package com.huanshankeji.compose.material3

import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.Attrs
import org.w3c.dom.HTMLElement

internal fun Modifier.toCommonButtonAttrs(onClick: () -> Unit): Attrs<HTMLElement> =
    toAttrs {
        onClick { onClick() }
    }
