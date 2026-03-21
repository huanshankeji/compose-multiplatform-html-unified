package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdOutlinedSegmentedButtonSet
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLElement

@MaterialWebLabsApi
@Composable
actual fun SingleChoiceSegmentedButtonRow(
    modifier: Modifier,
    space: Dp?,
    content: @Composable SingleChoiceSegmentedButtonRowScope.() -> Unit,
) =
    MdOutlinedSegmentedButtonSet(attrs = modifier.toAttrs()) {
        SingleChoiceSegmentedButtonRowScope(this).content()
    }

@MaterialWebLabsApi
@Composable
actual fun MultiChoiceSegmentedButtonRow(
    modifier: Modifier,
    space: Dp?,
    content: @Composable MultiChoiceSegmentedButtonRowScope.() -> Unit,
) =
    MdOutlinedSegmentedButtonSet(multiselect = true, attrs = modifier.toAttrs()) {
        MultiChoiceSegmentedButtonRowScope(this).content()
    }

actual class SingleChoiceSegmentedButtonRowScope(val elementScope: ElementScope<HTMLElement>)

actual class MultiChoiceSegmentedButtonRowScope(val elementScope: ElementScope<HTMLElement>)
