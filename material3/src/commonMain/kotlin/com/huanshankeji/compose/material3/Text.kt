package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.material3.ext.MaterialText
import com.huanshankeji.compose.material3.ext.TaglessText
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.style.TextAlign
import com.huanshankeji.compose.ui.text.style.TextDecoration
import com.huanshankeji.compose.ui.text.style.TextOverflow

/**
 * The `com.huanshankeji.compose.material3.Text` function
 * can be easily confused with other Composable functions named `Text`
 * such as `androidx.compose.material3.Text` and `org.jetbrains.compose.web.dom.Text`
 * if not careful.
 * [TaglessText] is recommended over this one when there is no custom [modifier].
 * @see TaglessText
 * @see MaterialText
 */
@Composable
expect fun Text(text: String, modifier: Modifier = Modifier, color: Color? = null)

@Composable
expect fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
)

@Composable
expect fun Text(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
)
