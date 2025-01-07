package com.huanshankeji.compose.material2.lazy.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material2.ext.TaglessText
import com.huanshankeji.compose.ui.Modifier

expect class ListScope {
    fun item(key: Any? = null, contentType: Any? = null, content: @Composable ItemScope.() -> Unit)

    fun items(
        count: Int,
        key: ((index: Int) -> Any)? = null,
        contentType: (index: Int) -> Any? = { null },
        itemContent: @Composable ItemScope.(index: Int) -> Unit
    )

    fun group(
        key: Any? = null,
        contentType: Any? = null,
        headerContent: @Composable HeaderScope.() -> Unit,
        content: ListScope.() -> Unit
    )

    @Composable
    fun ItemScope.ListItemContent(listItemComponents: ListItemComponents)
}

expect class ItemScope

expect class HeaderScope

class ListItemComponents(
    val text: @Composable () -> Unit,
    val secondaryText: @Composable (() -> Unit)? = null
) {
    constructor(text: String, secondaryText: String? = null) : this(
        { TaglessText(text) },
        secondaryText?.let { { TaglessText(it) } }
    )
}

// see https://youtrack.jetbrains.com/issue/KT-20427

fun ListScope.conventionalItem(key: Any? = null, contentType: Any? = null, content: ListItemComponents) =
    item(key, contentType) {
        ListItemContent(content)
    }

fun ListScope.conventionalItems(
    count: Int,
    key: ((index: Int) -> Any)? = null,
    contentType: (index: Int) -> Any? = { null },
    itemContent: (index: Int) -> ListItemComponents
) =
    items(count, key, contentType) { index ->
        ListItemContent(itemContent(index))
    }

/**
 * The current implementation is not actually lazy on web, but it seems not necessary to be.
 */
@Composable
expect fun List(modifier: Modifier = Modifier, content: ListScope.() -> Unit)

/**
 * An alias for [List] that is more similar to `androidx.compose.foundation.layout.LazyColumn`.
 */
@Composable
fun LazyColumnList(modifier: Modifier = Modifier, content: ListScope.() -> Unit) =
    List(modifier, content)
