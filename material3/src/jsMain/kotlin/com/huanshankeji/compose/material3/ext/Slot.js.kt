package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.html.material3.ISlot
import com.huanshankeji.compose.html.material3.SlotScope
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.compose.ui.toCommonModifier
import com.varabyte.kobweb.compose.ui.attrsModifier

@ExperimentalApi
@Composable
fun <Slot : ISlot, TSlotScope : SlotScope<Slot>> TSlotScope.slotModifier(slot: Slot): Modifier =
    PlatformModifier.attrsModifier { slot(slot) }.toCommonModifier()

@ExperimentalApi
@Composable
fun <Slot : ISlot, SlotScopeT : SlotScope<Slot>> SlotScopeT.contentWithSlot(
    content: @Composable (Modifier) -> Unit, slot: Slot
) =
    content(slotModifier(slot))

@ExperimentalApi
@Composable
fun <Slot : ISlot, SlotScopeT : SlotScope<Slot>> SlotScopeT.nullableContentWithSlot(
    content: @Composable ((Modifier) -> Unit)?, slot: Slot
) =
    content?.let { contentWithSlot(it, slot) }


// These 2 functions are not actually used now.
// Consider adding a Scope parameter to the returning lambda too.

@ExperimentalApi
fun <Slot : ISlot, SlotScopeT : SlotScope<Slot>> SlotScopeT.toSlotContent(
    content: @Composable (Modifier) -> Unit, slot: Slot
): @Composable () -> Unit =
    { contentWithSlot(content, slot) }

@ExperimentalApi
fun <Slot : ISlot, SlotScopeT : SlotScope<Slot>> SlotScopeT.toNullableSlotContent(
    content: @Composable ((Modifier) -> Unit)?, slot: Slot
): @Composable (() -> Unit)? =
    content?.let { toSlotContent(it, slot) }
