package com.huanshankeji.compose.foundation.lazy

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Arrangement
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

// copied and adapted from `LazyDsl.kt` in `androidx.compose.foundation.lazy`


/*
@LazyScopeMarker
@JvmDefaultWithCompatibility
*/
expect class LazyListScope {
    fun item(
        key: Any? = null,
        contentType: Any? = null,
        content: @Composable LazyItemScope.() -> Unit
    )

    fun items(
        count: Int,
        key: ((index: Int) -> Any)? = null,
        contentType: (index: Int) -> Any? = { null },
        itemContent: @Composable LazyItemScope.(index: Int) -> Unit
    )

    /*
    @ExperimentalFoundationApi
    fun stickyHeader(
        key: Any? = null,
        contentType: Any? = null,
        content: @Composable LazyItemScope.() -> Unit
    )
    */
}

inline fun <T> LazyListScope.items(
    items: List<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    itemContent(items[it])
}

inline fun <T> LazyListScope.itemsIndexed(
    items: List<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    itemContent(it, items[it])
}

inline fun <T> LazyListScope.items(
    items: Array<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    itemContent(items[it])
}

inline fun <T> LazyListScope.itemsIndexed(
    items: Array<T>,
    noinline key: ((index: Int, item: T) -> Any)? = null,
    crossinline contentType: (index: Int, item: T) -> Any? = { _, _ -> null },
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) = items(
    count = items.size,
    key = if (key != null) { index: Int -> key(index, items[index]) } else null,
    contentType = { index -> contentType(index, items[index]) }
) {
    itemContent(it, items[it])
}

/**
 * No need to be lazy on JS.
 */
@Composable
expect fun LazyRow(
    modifier: Modifier = Modifier,
    //state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues? = null,
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    //flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    //userScrollEnabled: Boolean = true, // This property works together with `state`.
    content: LazyListScope.() -> Unit
)

/**
 * No need to be lazy on JS.
 */
@Composable
expect fun LazyColumn(
    modifier: Modifier = Modifier,
    //state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues? = null,
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    //flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    //userScrollEnabled: Boolean = true, // This property works together with `state`.
    content: LazyListScope.() -> Unit
)
