package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.huanshankeji.compose.material.icons.Icons
import com.huanshankeji.compose.material.icons.filled.ArrowDropDown
import com.huanshankeji.compose.material3.Icon
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.draw.rotate
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.attributes.AttrsScope
import org.w3c.dom.HTMLElement

@Composable
actual fun ExposedDropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier,
    content: @Composable ExposedDropdownMenuBoxScope.() -> Unit
) =
    MdMenuBox(modifier) {
        ExposedDropdownMenuBoxScope(remember { mutableStateOf(null) }, expanded, onExpandedChange).content()
        // The menu doesn't show with this implementation.
        /*
        val (anchorElement, setAnchorElement) = remember { mutableStateOf<HTMLElement?>(null) }
        ExposedDropdownMenuBoxScope(anchorElement, setAnchorElement, expanded, onExpandedChange).content()
        */
    }

actual class ExposedDropdownMenuBoxScope(
    val anchorElementState: MutableState<HTMLElement?>, val expanded: Boolean, val onExpandedChange: (Boolean) -> Unit
    //val anchorElement: HTMLElement?, val setAnchorElement: (HTMLElement?) -> Unit, val expanded: Boolean, val onExpandedChange: (Boolean) -> Unit // The menu doesn't show with this implementation.
) {
    //var anchorElement by anchorElementState
    //val (anchorElement, setAnchorElement) = anchorElementState
    /*
    companion object {
        const val ANCHOR_ID = "anchor"
    }
    */

    actual fun Modifier.menuAnchor(): Modifier =
        platformModify {
            attrsModifier {
                //id(ANCHOR_ID) // An alternative approach by setting IDs. Duplicate IDs are semantically incorrect, however.

                refSetAnchorElementState(anchorElementState.component2())
                // only fired when `expanded` set to `true` to be consistent with the `androidx.compose` one behavior
                if (!expanded)
                    onClick {
                        onExpandedChange(true)
                    }
            }
        }

    @Composable
    actual fun ExposedDropdownMenu(
        expanded: Boolean,
        onDismissRequestComposeUi: () -> Unit,
        onCloseJsDom: () -> Unit,
        modifier: Modifier,
        content: @Composable () -> Unit
    ) =
        CommonDropdownMenu(
            //ANCHOR_ID, // An alternative approach by setting IDs. Duplicate IDs are semantically incorrect, however.
            expanded,
            onCloseJsDom,
            // The following is identical to inlining `mdMenuModifier(anchorElementState.value, modifier)` but probably due to some Compose compiler bugs invoking it directly doesn't work. FIXME when the bug is fixed
            {
                ref {
                    it.anchorElement = anchorElementState.value
                    onDispose {}
                }

                modifier.platformModifier.toAttrs<AttrsScope<*>>()()
            },
            content = { content() }
            //onExpandedChange = onExpandedChange // It seems there's no need to pass here as `onExpandedChange` will be called when clicking the text view
        )
}

@Composable
actual fun ExposedDropdownMenuBoxScope.ExposedDropdownMenuBoxTextField(
    expanded: Boolean, args: ExposedDropdownMenuBoxTextFieldArgs
) =
    with(args) {
        // adapted from the `composeUi` one
        TextField(
            modifier = Modifier.menuAnchor(/*MenuAnchorType.PrimaryNotEditable*/),
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            singleLine = singleLine,
            label = label,
            trailingIcon = { modifier ->
                Icon(Icons.Filled.ArrowDropDown, null, if (expanded) modifier.rotate(180f) else modifier)
            }
        )
    }
