package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpOffset
import com.huanshankeji.compose.toContentWithoutModifier
import com.huanshankeji.compose.toNullableContentWithoutModifier
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun DropdownMenu(
    expanded: Boolean,
    onDismissRequestComposeUi: () -> Unit,
    onCloseJsDom: () -> Unit,
    modifier: Modifier,
    offset: DpOffset,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.DropdownMenu(
        expanded,
        onDismissRequestComposeUi,
        modifier.platformModifier,
        offset
    ) {
        content()
    }

actual class DropdownMenuBoxScope {
    actual fun Modifier.menuAnchorJsDom(): Modifier =
        this // do nothing

    @Composable
    actual fun DropdownMenu(
        expanded: Boolean,
        onDismissRequestComposeUi: () -> Unit,
        onCloseJsDom: () -> Unit,
        modifier: Modifier,
        offset: DpOffset,
        content: @Composable () -> Unit
    ) =
        androidx.compose.material3.DropdownMenu(
            expanded, onDismissRequestComposeUi, modifier.platformModifier, offset
        ) { content() }
}

@Composable
actual fun DropdownMenuBox(content: @Composable DropdownMenuBoxScope.() -> Unit) =
    // There is currently no extra `Box` wrapping the content yet. Consider adding one. See https://stackoverflow.com/a/68728525/5082913.
    DropdownMenuBoxScope().content()

/**
 * @param onClick you are supposed to set the `expanded` state of the parent [DropdownMenu] or [ExposedDropdownMenuBoxScope.ExposedDropdownMenu] to `false` in this callback to ensure consistency on JS.
 */
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
    androidx.compose.material3.DropdownMenuItem(
        text.toContentWithoutModifier(),
        onClick,
        modifier.platformModifier,
        leadingIcon.toNullableContentWithoutModifier(),
        trailingIcon.toNullableContentWithoutModifier(),
        enabled
    )
