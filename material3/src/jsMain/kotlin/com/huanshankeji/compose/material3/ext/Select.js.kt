package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import androidx.compose.web.events.SyntheticEvent
import com.huanshankeji.compose.html.material3.*
import com.huanshankeji.compose.material3.contentFromComponents
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.attributes.AttrsScope

private fun mdSelectAttrs(
    modifier: Modifier,
    menuArgs: SelectMenuArgs
): AttrsScope<InternalSelectElement>.() -> Unit =
    modifier.toAttrs {
        /*
        onInput { event ->
            console.log("onInput: ", event)
            console.log("value: ", event.target.value)
        }
        onChange { event ->
            console.log("onChange: ", event, event.target.value)
            console.log("value: ", event.target.value)
        }
        */
        onClosing<SyntheticEvent<*>> { menuArgs.onCloseJsDom() }
    }

/**
 * @see CommonDropdownMenu
 */
@Composable
actual fun FilledSelect(
    expandedComposeUi: Boolean,
    onExpandedChangeComposeUi: (Boolean) -> Unit,
    valueJsDom: String,
    modifier: Modifier,
    textFieldArgs: SelectTextFieldArgs,
    menuArgs: SelectMenuArgs
) =
    MdFilledSelect(
        label = textFieldArgs.label,
        supportingText = textFieldArgs.supportingText,
        error = textFieldArgs.isError.isTrueOrNull(),
        disabled = textFieldArgs.enabled.isFalseOrNull(),
        value = valueJsDom,
        clampMenuWidth = menuArgs.matchAnchorWidth,
        attrs = mdSelectAttrs(modifier, menuArgs)
    ) {
        menuArgs.content()
    }

@Composable
actual fun OutlinedSelect(
    expandedComposeUi: Boolean,
    onExpandedChangeComposeUi: (Boolean) -> Unit,
    valueJsDom: String,
    modifier: Modifier,
    textFieldArgs: SelectTextFieldArgs,
    menuArgs: SelectMenuArgs
) =
    MdOutlinedSelect(
        label = textFieldArgs.label,
        supportingText = textFieldArgs.supportingText,
        error = textFieldArgs.isError.isTrueOrNull(),
        disabled = textFieldArgs.enabled.isFalseOrNull(),
        value = valueJsDom,
        attrs = mdSelectAttrs(modifier, menuArgs)
    ) {
        menuArgs.content()
    }

/**
 * @see DropdownMenuItem
 * @see MdSelectOption
 */
@Composable
actual fun SelectOption(
    text: @Composable ((Modifier) -> Unit),
    onClick: () -> Unit,
    selectedJsDom: Boolean,
    modifier: Modifier,
    leadingIcon: @Composable ((Modifier) -> Unit)?,
    trailingIcon: @Composable ((Modifier) -> Unit)?,
    enabled: Boolean,
    valueJsDom: String?
) =
    // copied and adapted from `DropdownMenuItem`
    // Selection is controlled via the `value` attribute on the parent select element, not via `selected` here.
    MdSelectOption(
        enabled.isFalseOrNull(),
        value = valueJsDom,
        attrs = modifier.toAttrs {
            onClick { onClick() }
        }) {
        contentFromComponents(text, leadingIcon, trailingIcon)
    }
