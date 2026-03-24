package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.style.TextDecoration

// copied and adapted from `androidx.compose.ui.text.SpanStyle`
@Immutable
class SpanStyle(
    val color: Color? = null,
    val fontSize: TextUnit = TextUnit.Unspecified,
    val fontWeight: FontWeight? = null,
    val fontStyle: FontStyle? = null,
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    val textDecoration: TextDecoration? = null,
)
