package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material2.ext.TaglessText
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

/**
 * The `com.huanshankeji.compose.material.Text` function
 * can be easily confused with other Composable functions named `Text`
 * such as `androidx.compose.material.Text` and `org.jetbrains.compose.web.dom.Text`
 * if not careful.
 * [TaglessText] is recommended over this one when there is no custom [modifier].
 * @see com.huanshankeji.compose.material2.ext.MaterialText
 */
@Composable
expect fun Text(text: String, modifier: Modifier = Modifier, color: Color? = null)
