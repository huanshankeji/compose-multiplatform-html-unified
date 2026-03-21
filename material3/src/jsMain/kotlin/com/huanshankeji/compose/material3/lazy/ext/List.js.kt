package com.huanshankeji.compose.material3.lazy.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.imitateComposeUiLayoutVerticalScrollPlatformModifier
import com.huanshankeji.compose.html.material3.MdList
import com.huanshankeji.compose.html.material3.MdListItemScope
import com.huanshankeji.compose.html.material3.MdListItemType
import com.huanshankeji.compose.html.material3.MdListScope
import com.huanshankeji.compose.material3.ext.contentFromComponents
import com.huanshankeji.compose.runtime.DeferredComposableRunner
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.varabyte.kobweb.compose.ui.toAttrs

actual class ListScope(val mdListScope: MdListScope) {
    private val deferredComposableRunner = DeferredComposableRunner()

    private fun addComposable(composable: @Composable () -> Unit) =
        deferredComposableRunner.addComposable(composable)

    @Composable
    internal fun ComposableRun(content: ListScope.() -> Unit) =
        deferredComposableRunner.ComposableRun { content() }


    @Composable
    private fun ListItem(components: ListItemComponents) =
        mdListScope.MdListItem(
            type = if (components.isInteractiveJsDom) MdListItemType.Button else null,
            attrs = components.contentModifier.toAttrs(),
        ) {
            contentFromComponents(components)
        }


    actual fun item(
        key: Any?,
        contentType: Any?,
        content: @Composable ItemScope.() -> Unit,
    ) = addComposable {
        mdListScope.MdListItem { ItemScope(this).content() }
    }

    actual fun conventionalItem(
        key: Any?,
        contentType: Any?,
        content: ListItemComponents,
    ) = addComposable {
        ListItem(content)
    }

    actual fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable ItemScope.(index: Int) -> Unit,
    ) = addComposable {
        repeat(count) { index ->
            mdListScope.MdListItem { ItemScope(this).itemContent(index) }
        }
    }

    actual fun conventionalItems(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: (index: Int) -> ListItemComponents,
    ) = addComposable {
        repeat(count) { index ->
            ListItem(itemContent(index))
        }
    }
}

actual class ItemScope(val mdListItemScope: MdListItemScope)

/*
@Composable
fun PrimitiveList() =
    TODO() as Unit
*/

@Composable
actual fun List(
    modifier: Modifier,
    content: ListScope.() -> Unit,
) =
    MdList(imitateComposeUiLayoutVerticalScrollPlatformModifier.then(modifier.platformModifier).toAttrs()) {
        ListScope(this).ComposableRun(content)
    }
