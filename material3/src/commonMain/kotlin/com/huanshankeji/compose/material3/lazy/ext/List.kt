package com.huanshankeji.compose.material3.lazy.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.ext.matchPositionRelativeParentJsDom
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material3.ext.toNullableContentWithModifier
import com.huanshankeji.compose.material3.ext.toNullableTextWithModifier
import com.huanshankeji.compose.material3.ext.toTextWithModifier
import com.huanshankeji.compose.ui.Modifier

expect class ListScope {
    fun item(key: Any? = null, contentType: Any? = null, content: @Composable ItemScope.() -> Unit)

    // the word "conventional" added to be consistent with `conventionalItems`
    fun conventionalItem(key: Any? = null, contentType: Any? = null, content: ListItemComponents)

    fun items(
        count: Int,
        key: ((index: Int) -> Any)? = null,
        contentType: (index: Int) -> Any? = { null },
        itemContent: @Composable ItemScope.(index: Int) -> Unit
    )

    // the word "conventional" added to avoid clashing with `items`
    fun conventionalItems(
        count: Int,
        key: ((index: Int) -> Any)? = null,
        contentType: (index: Int) -> Any? = { null },
        itemContent: (index: Int) -> ListItemComponents
    )
}

expect class ItemScope

/**
 * @param isInteractiveJsDom whether the item is interactive on JS DOM,
 * aka whether it shows effects when the mouse pointer hovers above it or when it gets clicked.
 * On the `androidx.compose` targets, use `Modifier.clickable` for the same effect.
 */
class ListItemComponents(
    val contentModifier: Modifier = Modifier,
    val isInteractiveJsDom: Boolean,
    val headline: @Composable (Modifier) -> Unit,
    val start: @Composable ((Modifier) -> Unit)? = null,
    val end: @Composable ((Modifier) -> Unit)? = null,
    val supportingText: @Composable ((Modifier) -> Unit)? = null,
    val trailingSupportingText: @Composable ((Modifier) -> Unit)? = null,
    val overline: @Composable ((Modifier) -> Unit)? = null
) {
    constructor(
        contentModifier: Modifier = Modifier,
        isInteractiveJsDom: Boolean,
        headline: String,
        start: Icon? = null,
        end: Icon? = null,
        supportingText: String? = null,
        trailingSupportingText: String? = null,
        overline: String? = null
    ) : this(
        contentModifier,
        isInteractiveJsDom,
        headline.toTextWithModifier(),
        start.toNullableContentWithModifier(),
        end.toNullableContentWithModifier(),
        supportingText.toNullableTextWithModifier(),
        trailingSupportingText.toNullableTextWithModifier(),
        overline.toNullableTextWithModifier()
    )
}

/**
 * On JS DOM, if there isn't a parent with a fixed height
 * and you want the list height to be constraint to the available space of the parent instead of expanding the parent,
 * set "position:relative;" on the parent element and use [Modifier.matchPositionRelativeParentJsDom].
 */
@Composable
expect fun List(modifier: Modifier = Modifier, content: ListScope.() -> Unit)

/**
 * An alias for [List] that is more similar to `androidx.compose.foundation.layout.LazyColumn`.
 */
@Composable
fun LazyColumnList(modifier: Modifier = Modifier, content: ListScope.() -> Unit) =
    List(modifier, content)
