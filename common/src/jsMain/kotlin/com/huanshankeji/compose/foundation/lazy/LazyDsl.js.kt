package com.huanshankeji.compose.foundation.lazy

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.imitateComposeUiLayoutHorizontalScrollPlatformModifier
import com.huanshankeji.compose.foundation.imitateComposeUiLayoutVerticalScrollPlatformModifier
import com.huanshankeji.compose.foundation.layout.*
import com.huanshankeji.compose.runtime.DeferredComposableRunner
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.compose.ui.toCommonModifier

/*
@LazyScopeMarker
@JvmDefaultWithCompatibility
*/
actual class LazyListScope {
    private val deferredComposableRunner = DeferredComposableRunner()

    private fun addComposable(composable: @Composable () -> Unit) =
        deferredComposableRunner.addComposable(composable)

    @Composable
    internal fun ComposableRun(content: LazyListScope.() -> Unit) =
        deferredComposableRunner.ComposableRun { content() }

    actual fun item(
        key: Any?,
        contentType: Any?,
        content: @Composable LazyItemScope.() -> Unit
    ) =
        addComposable { LazyItemScope.content() }

    actual fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable LazyItemScope.(index: Int) -> Unit
    ) =
        addComposable {
            repeat(count) { index ->
                LazyItemScope.itemContent(index)
            }
        }
}

private fun commonLazyModifier(
    scrollPlatformModifier: PlatformModifier,
    contentPadding: PaddingValues?,
    modifier: Modifier
) = scrollPlatformModifier.toCommonModifier().run {
    if (contentPadding !== null) padding(contentPadding)
    else this
}.then(modifier)

@Composable
actual fun LazyRow(
    modifier: Modifier,
    contentPadding: PaddingValues?,
    reverseLayout: Boolean, // This parameter is not used yet but affects the arrangement in the default argument of the corresponding `expect` function.
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment: Alignment.Vertical,
    content: LazyListScope.() -> Unit
) =
    Row(
        commonLazyModifier(imitateComposeUiLayoutHorizontalScrollPlatformModifier, contentPadding, modifier),
        horizontalArrangement,
        verticalAlignment
    ) {
        LazyListScope().ComposableRun(content)
    }

@Composable
actual fun LazyColumn(
    modifier: Modifier,
    contentPadding: PaddingValues?,
    reverseLayout: Boolean, // This parameter is not used yet but affects the arrangement in the default argument of the corresponding `expect` function.
    verticalArrangement: Arrangement.Vertical,
    horizontalAlignment: Alignment.Horizontal,
    content: LazyListScope.() -> Unit
) =
    Column(
        commonLazyModifier(imitateComposeUiLayoutVerticalScrollPlatformModifier, contentPadding, modifier),
        verticalArrangement,
        horizontalAlignment
    ) {
        LazyListScope().ComposableRun(content)
    }
