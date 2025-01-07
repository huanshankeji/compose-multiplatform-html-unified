package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpOffset
import androidx.compose.web.events.SyntheticEvent
import com.huanshankeji.compose.html.material3.*
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.Attrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement

@Composable
internal fun CommonDropdownMenu(
    //anchor: String?,
    expanded: Boolean,
    onCloseJsDom: () -> Unit,
    //onDismissRequest: () -> Unit,
    attrs: Attrs<MdMenuElement>?,
    offset: DpOffset = defaultDpOffset,
    content: @Composable ElementScope<MdMenuElement>.() -> Unit
    //onExpandedChange: ((Boolean) -> Unit)? = null
) =
    MdMenu(
        open = expanded.isTrueOrNull(),
        xOffset = offset.x.value.takeIf { it != 0f },
        yOffset = offset.y.value.takeIf { it != 0f },
        attrs = {
            /*onExpandedChange?.let {
                onOpening<SyntheticEvent<*>> { it(true) }
                onClosing<SyntheticEvent<*>> {
                    onDismissRequest()
                    it(false)
                }
            }*/
            //onClosing<SyntheticEvent<*>> { onDismissRequest() } // the `androidx.compose` one invokes `onDismissRequest` only when the menu is closed and an item is not selected.
            onClosing<SyntheticEvent<*>> { onCloseJsDom() }

            attrs?.invoke(this)
        }, content = content
    )

internal fun AttrsScope<*>.refSetAnchorElementState(setAnchorElement: (HTMLElement?) -> Unit) =
    ref {
        setAnchorElement(it as HTMLElement)
        onDispose { setAnchorElement(null) }
    }

//@Suppress("NOTHING_TO_INLINE")
internal /*inline*/ fun AttrsScope<MdMenuElement>.refSetMdMenuElementAnchorElement(anchorElement: HTMLElement?) =
    ref {
        //anchorElement?.let { anchorElement -> it.anchorElement = anchorElement }
        it.anchorElement = anchorElement
        onDispose {}
    }

//@Suppress("NOTHING_TO_INLINE")
internal /*inline*/ fun mdMenuAttrs(
    anchorElement: HTMLElement?,
    modifier: Modifier
): AttrsScope<MdMenuElement>.() -> Unit =
    {
        refSetMdMenuElementAnchorElement(anchorElement)

        modifier.platformModifier.toAttrs<AttrsScope<*>>()()
    }

@Composable
internal fun MdMenuBox(modifier: Modifier, content: @Composable ElementScope<HTMLDivElement>.() -> Unit) =
    Div(
        {
            // see https://github.com/material-components/material-web/blob/main/docs/components/menu.md#usage
            style {
                position(Position.Relative)
            }
            modifier.toAttrs<AttrsScope<*>>()()
        },
        content = content
    )


@Composable
actual fun DropdownMenu(
    expanded: Boolean,
    onDismissRequestComposeUi: () -> Unit,
    onCloseJsDom: () -> Unit,
    modifier: Modifier,
    offset: DpOffset,
    content: @Composable () -> Unit
) =
    CommonDropdownMenu(expanded, onCloseJsDom, modifier.toAttrs(), offset) { content() }

actual class DropdownMenuBoxScope(anchorElementState: MutableState<HTMLElement?>) {
    var anchorElement by anchorElementState
    actual fun Modifier.menuAnchorJsDom(): Modifier =
        platformModify {
            attrsModifier {
                refSetAnchorElementState { anchorElement = it }
            }
        }

    @Composable
    actual fun DropdownMenu(
        expanded: Boolean,
        onDismissRequestComposeUi: () -> Unit,
        onCloseJsDom: () -> Unit,
        modifier: Modifier,
        offset: DpOffset,
        content: @Composable () -> Unit
    ) =
        CommonDropdownMenu(
            expanded, onCloseJsDom,
            // The following is identical to inlining `mdMenuModifier(anchorElementState.value, modifier)` but probably due to some Compose compiler bugs invoking it directly doesn't work. FIXME when the bug is fixed
            {
                ref {
                    it.anchorElement = anchorElement
                    onDispose {}
                }

                modifier.platformModifier.toAttrs<AttrsScope<*>>()()
            }, offset
        ) { content() }
}

@Composable
actual fun DropdownMenuBox(content: @Composable DropdownMenuBoxScope.() -> Unit) =
    MdMenuBox(Modifier) {
        DropdownMenuBoxScope(remember { mutableStateOf(null) }).content()
    }

@Composable
actual fun DropdownMenuItem(
    text: @Composable (Modifier) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier,
    leadingIcon: @Composable ((Modifier) -> Unit)?,
    trailingIcon: @Composable ((Modifier) -> Unit)?,
    enabled: Boolean,
    keepOpenJsDom: Boolean
) =
    MdMenuItem(
        enabled.isFalseOrNull(),
        keepOpen = keepOpenJsDom.isTrueOrNull(),
        attrs = modifier.toAttrs {
            onClick {
                onClick()
            }
        }) {
        text(Modifier.platformModify { attrsModifier { slot(MdMenuItemScope.Slot.Headline) } })
        leadingIcon?.let { it(Modifier.platformModify { attrsModifier { slot(MdMenuItemScope.Slot.Start) } }) }
        trailingIcon?.let { it(Modifier.platformModify { attrsModifier { slot(MdMenuItemScope.Slot.End) } }) }
    }
