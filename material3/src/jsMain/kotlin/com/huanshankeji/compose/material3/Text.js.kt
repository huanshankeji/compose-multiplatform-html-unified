package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import com.huanshankeji.compose.foundation.text.ext.CommonBasicText
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

@Composable
actual fun Text(text: String, modifier: Modifier, color: Color?) =
    @OptIn(InternalComposeApi::class)
    CommonBasicText(text, modifier, color)
