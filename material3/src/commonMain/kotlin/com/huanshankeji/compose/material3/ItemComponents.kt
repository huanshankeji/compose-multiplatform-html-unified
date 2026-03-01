package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Corresponds to the slots of Material Web `md-item`.
 *
 * @param start corresponds to `leadingIcon` in Compose UI.
 * @param end corresponds to `trailingIcon` in Compose UI.
 */
interface ItemComponents {
    val headline: @Composable (Modifier) -> Unit
    val start: @Composable ((Modifier) -> Unit)?
    val end: @Composable ((Modifier) -> Unit)?
    val supportingText: @Composable ((Modifier) -> Unit)?
    val trailingSupportingText: @Composable ((Modifier) -> Unit)?
    val container: @Composable ((Modifier) -> Unit)?
    val overline: @Composable ((Modifier) -> Unit)?
}