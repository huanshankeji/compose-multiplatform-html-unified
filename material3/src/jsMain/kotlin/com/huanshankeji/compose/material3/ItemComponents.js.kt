package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.html.material3.IMdItemScope
import com.huanshankeji.compose.html.material3.IMdItemScope.Slot
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.compose.ui.toCommonModifier
import com.varabyte.kobweb.compose.ui.attrsModifier

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
    overline: @Composable ((Modifier) -> Unit)? = null
) {
    headline(PlatformModifier.attrsModifier { slot(Slot.Headline) }.toCommonModifier())
    start?.invoke(PlatformModifier.attrsModifier { slot(Slot.Start) }.toCommonModifier())
    end?.invoke(PlatformModifier.attrsModifier { slot(Slot.End) }.toCommonModifier())
    supportingText?.invoke(
        PlatformModifier.attrsModifier { slot(Slot.SupportingText) }.toCommonModifier()
    )
    trailingSupportingText?.invoke(
        PlatformModifier.attrsModifier { slot(Slot.TrailingSupportingText) }.toCommonModifier()
    )
    container?.invoke(PlatformModifier.attrsModifier { slot(Slot.Container) }.toCommonModifier())
    overline?.invoke(PlatformModifier.attrsModifier { slot(Slot.Overline) }.toCommonModifier())
}

@ExperimentalApi
@Composable
fun IMdItemScope.contentFromComponents(itemComponents: ItemComponents) = with(itemComponents) {
    contentFromComponents(headline, start, end, supportingText, trailingSupportingText, container, overline)
}
