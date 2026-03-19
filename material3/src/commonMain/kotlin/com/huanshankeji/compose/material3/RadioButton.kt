package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * @see com.huanshankeji.compose.material3.ext.RadioButton
 * @see com.huanshankeji.compose.material3.ext.RadioButtonRow
 */
@Composable
fun RadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) =
    com.huanshankeji.compose.material3.ext.RadioButton(selected, onClick, modifier, enabled)
