package com.huanshankeji.compose.material

//import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Element
import com.huanshankeji.compose.ui.ModifierOrAttrs

expect abstract class ListElement : Element

/** @see LazyListScope */
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
}

expect class ItemScope
expect class HeaderScope

@Composable
expect fun List(modifierOrAttrs: ModifierOrAttrs<ListElement> = null, content: ListScope.() -> Unit)

/**
 * An alias for [List] that follows the name of [androidx.compose.foundation.lazy.LazyColumn].
 * The current implementation is not actually lazy on web.
 */
@Composable
fun LazyColumn(modifierOrAttrs: ModifierOrAttrs<ListElement> = null, content: ListScope.() -> Unit) =
    List(modifierOrAttrs, content)
