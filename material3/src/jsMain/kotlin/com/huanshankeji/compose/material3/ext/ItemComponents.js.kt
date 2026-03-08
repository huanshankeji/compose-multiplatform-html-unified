package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.html.material3.IMdItemScope
import com.huanshankeji.compose.html.material3.IMdItemScope.Slot
import com.huanshankeji.compose.ui.Modifier

/**
 * @see ItemComponents
 */
@ExperimentalApi
@Composable
fun IMdItemScope.contentFromComponents(
    headline: @Composable (Modifier) -> Unit,
    start: @Composable ((Modifier) -> Unit)? = null,
    end: @Composable ((Modifier) -> Unit)? = null,
    supportingText: @Composable ((Modifier) -> Unit)? = null,
    trailingSupportingText: @Composable ((Modifier) -> Unit)? = null,
    container: @Composable ((Modifier) -> Unit)? = null,
    overline: @Composable ((Modifier) -> Unit)? = null,
) {
    contentWithSlot(headline, Slot.Headline)
    nullableContentWithSlot(start, Slot.Start)
    nullableContentWithSlot(end, Slot.End)
    nullableContentWithSlot(supportingText, Slot.SupportingText)
    nullableContentWithSlot(trailingSupportingText, Slot.TrailingSupportingText)
    nullableContentWithSlot(container, Slot.Container)
    nullableContentWithSlot(overline, Slot.Overline)
}

@ExperimentalApi
@Composable
fun IMdItemScope.contentFromComponents(itemComponents: ItemComponents) = with(itemComponents) {
    contentFromComponents(headline, start, end, supportingText, trailingSupportingText, container, overline)
}
