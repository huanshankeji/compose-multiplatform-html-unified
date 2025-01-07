package com.huanshankeji.compose.material2.lazy.ext

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyItemScope as PlatformLazyItemScope
import androidx.compose.foundation.lazy.LazyListScope as PlatformLazyListScope

actual class ListScope(val platformValue: PlatformLazyListScope) {
    actual fun item(key: Any?, contentType: Any?, content: @Composable ItemScope.() -> Unit) =
        platformValue.item(key, contentType) { ItemScope(this).content() }

    actual fun items(
        count: Int,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        itemContent: @Composable ItemScope.(index: Int) -> Unit
    ) =
        platformValue.items(count, key, contentType) { ItemScope(this).itemContent(it) }

    actual fun group(
        key: Any?,
        contentType: Any?,
        headerContent: @Composable HeaderScope.() -> Unit,
        content: ListScope.() -> Unit
    ) {
        @OptIn(ExperimentalFoundationApi::class)
        platformValue.stickyHeader(key, contentType) { HeaderScope(this).headerContent() }
        content()
    }

    @Composable
    actual fun ItemScope.ListItemContent(listItemComponents: ListItemComponents) =
        with(listItemComponents) {
            @OptIn(ExperimentalMaterialApi::class)
            ListItem(secondaryText = secondaryText, text = text)
        }
}

actual class ItemScope(val platformValue: PlatformLazyItemScope)
actual typealias HeaderScope = ItemScope

@Composable
actual fun List(modifier: Modifier, content: ListScope.() -> Unit) =
    // Note that it's actually in the `foundation` package.
    androidx.compose.foundation.lazy.LazyColumn(modifier.platformModifier) {
        ListScope(this).content()
    }
