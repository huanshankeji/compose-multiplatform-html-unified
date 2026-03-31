package com.huanshankeji.compose.foundation.text

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.ColorProducer
import com.huanshankeji.compose.ui.text.AnnotatedString
import com.huanshankeji.compose.ui.text.style.TextOverflow

@Deprecated(
    "This is not needed as there is a `TaglessBasicText` composable. Use the one below.",
    level = DeprecationLevel.HIDDEN
)
@Composable
fun BasicText(text: String) =
    BasicText(text, Modifier)

/**
 * The following Compose UI `BasicText` parameters are not yet supported:
 * - `style: TextStyle` — requires porting `TextStyle` (see #131)
 * - `onTextLayout: ((TextLayoutResult) -> Unit)?` — requires porting `TextLayoutResult`
 * - `autoSize: TextAutoSize?` — requires porting `TextAutoSize`
 */
@Composable
expect fun BasicText(
    text: String,
    modifier: Modifier = Modifier,
    // style: TextStyle = TextStyle.Default, // #131
    // onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null,
    // autoSize: TextAutoSize? = null,
)

@Composable
expect fun BasicText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    // style: TextStyle = TextStyle.Default, // #131
    // onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    // inlineContent: Map<String, InlineTextContent> = mapOf(),
    color: ColorProducer? = null,
    // autoSize: TextAutoSize? = null,
)
