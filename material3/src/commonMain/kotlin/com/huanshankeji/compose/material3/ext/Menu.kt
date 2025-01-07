package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

internal val defaultDpOffset = DpOffset(0.dp, 0.dp)

/**
 * @see DropdownMenuBoxScope.DropdownMenu
 */
@Deprecated("This implementation doesn't have the `anchorElement` set on JS DOM and thus doesn't work directly. Use `DropdownMenuBox`, `DropdownMenuBoxScope.menuAnchorJs`, and `DropdownMenuBoxScope.DropdownMenu` instead.")
@Composable
expect fun DropdownMenu(
    expanded: Boolean,
    onDismissRequestComposeUi: () -> Unit,
    onCloseJsDom: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = defaultDpOffset,
    //scrollState: ScrollState = rememberScrollState(),
    content: @Composable () -> Unit
)

expect class DropdownMenuBoxScope {
    fun Modifier.menuAnchorJsDom(): Modifier

    /**
     * @param onDismissRequestComposeUi not supported on JS.
     * @param onCloseJsDom JS only
     * @see com.huanshankeji.compose.material3.ext.DropdownMenu
     */
    @Composable
    fun DropdownMenu(
        expanded: Boolean,
        onDismissRequestComposeUi: () -> Unit,
        onCloseJsDom: () -> Unit,
        modifier: Modifier = Modifier,
        offset: DpOffset = defaultDpOffset,
        //scrollState: ScrollState = rememberScrollState(),
        content: @Composable () -> Unit
    )
}

@Composable
expect fun DropdownMenuBox(content: @Composable DropdownMenuBoxScope.() -> Unit)

/**
 * @param keepOpenJsDom set to `true` for completely consistent behavior on JS to `androidx.compose`. However, if you set the `expanded` state to false in [onClick], doing this is unnecessary.
 */
@Composable
expect fun DropdownMenuItem(
    text: @Composable (Modifier) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable ((Modifier) -> Unit)? = null,
    trailingIcon: @Composable ((Modifier) -> Unit)? = null,
    enabled: Boolean = true,
    keepOpenJsDom: Boolean = false
)

@Composable
fun DropdownMenuItemWithMaterialIcons(
    text: @Composable (Modifier) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: Icon? = null,
    trailingIcon: Icon? = null,
    enabled: Boolean = true
) =
    DropdownMenuItem(
        text,
        onClick,
        modifier,
        leadingIcon.toNullableContentWithModifier(),
        trailingIcon.toNullableContentWithModifier(),
        enabled
    )
