package com.huanshankeji.compose.foundation.ext.css

import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.overflowX
import com.varabyte.kobweb.compose.css.overflowY
import org.jetbrains.compose.web.css.StyleScope

fun StyleScope.horizontalScroll() =
    overflowX(Overflow.Auto)

fun StyleScope.verticalScroll() =
    //overflowY(Overflow.Scroll)
    overflowY(Overflow.Auto)
