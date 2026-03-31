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
 *
 * The following Compose UI `Text` parameters are not yet supported:
 * - `autoSize: TextAutoSize?` — requires porting `TextAutoSize`
 * - `fontFamily: FontFamily?` — requires porting `FontFamily`
 * - `onTextLayout: ((TextLayoutResult) -> Unit)?` — requires porting `TextLayoutResult`
 * - `style: TextStyle` — requires porting `TextStyle` and `LocalTextStyle` (see #131); on JS DOM, `TextStyle` could enable mapping Material Design typography roles to semantic HTML elements (e.g., heading styles → `<h1>`–`<h6>`, body styles → `<p>`) for better HTML semantics (see also #17 for `MaterialTheme` support)
 * - `inlineContent: Map<String, InlineTextContent>` — AnnotatedString overload only; requires porting `InlineTextContent`
 *
 * @see TaglessText
 * @see MaterialText
 */
@Deprecated("Use the overload with more parameters.", level = DeprecationLevel.HIDDEN)
@Composable
expect fun Text(text: String, modifier: Modifier = Modifier, color: Color? = null)

@Composable
expect fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = null,
    // autoSize: TextAutoSize? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    // fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    // onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    // style: TextStyle = LocalTextStyle.current, // #131
)

@Composable
expect fun Text(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color? = null,
    // autoSize: TextAutoSize? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    // fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    // inlineContent: Map<String, InlineTextContent> = mapOf(),
    // onTextLayout: (TextLayoutResult) -> Unit = {},
    // style: TextStyle = LocalTextStyle.current, // #131
)
