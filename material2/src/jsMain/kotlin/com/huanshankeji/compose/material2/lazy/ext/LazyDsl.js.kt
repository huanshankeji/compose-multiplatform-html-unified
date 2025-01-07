package com.huanshankeji.compose.material2.lazy.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.imitateComposeUiLayoutVerticalScrollPlatformModifier
import com.huanshankeji.compose.runtime.DeferredComposableRunner
import com.huanshankeji.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import dev.petuska.kmdc.list.MDCList
import dev.petuska.kmdc.list.MDCListGroup
import dev.petuska.kmdc.list.MDCListScope
import dev.petuska.kmdc.list.Subheader
import dev.petuska.kmdc.list.item.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLHeadingElement
import org.w3c.dom.HTMLLIElement
import org.w3c.dom.HTMLUListElement

/**
 * This class contains mutable fields.
 * @see androidx.compose.foundation.lazy.LazyListScopeImpl
 */
actual class ListScope(
    val mdcListScope: MDCListScope<HTMLUListElement>
) {
    private val deferredComposableRunner = DeferredComposableRunner()

    private fun addComposable(composable: @Composable () -> Unit) =
        deferredComposableRunner.addComposable(composable)

    @Composable
    internal fun ComposableRun(content: ListScope.() -> Unit) =
        deferredComposableRunner.ComposableRun { content() }

    actual fun item(key: Any?, contentType: Any?, content: @Composable ItemScope.() -> Unit) = addComposable {
        mdcListScope.ListItem { ItemScope(this).content() }
    }

    actual fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable ItemScope.(index: Int) -> Unit
    ) = addComposable {
        repeat(count) { i ->
            mdcListScope.ListItem { ItemScope(this).itemContent(i) }
        }
    }

    actual fun group(
        key: Any?,
        contentType: Any?,
        headerContent: @Composable HeaderScope.() -> Unit,
        content: ListScope.() -> Unit
    ) = deferredComposableRunner.addComposable {
        MDCListGroup {
            Subheader {
                HeaderScope(this).headerContent()
            }
            MDCList {
                ListScope(this).ComposableRun(content)
            }
        }
    }

    @Composable
    actual fun ItemScope.ListItemContent(listItemComponents: ListItemComponents) =
        with(listItemComponents) {
            mdcListItemScope.Label {
                Primary { text() }
                secondaryText?.let { Secondary { it() } }
            }
        }
}

actual class ItemScope(val mdcListItemScope: MDCListItemScope<HTMLLIElement>)
actual class HeaderScope(val headingElementScope: ElementScope<HTMLHeadingElement>)

@Composable
actual fun List(modifier: Modifier, content: ListScope.() -> Unit) =
    MDCList(attrs = imitateComposeUiLayoutVerticalScrollPlatformModifier.then(modifier.platformModifier).toAttrs()) {
        ListScope(this).ComposableRun(content)
    }
