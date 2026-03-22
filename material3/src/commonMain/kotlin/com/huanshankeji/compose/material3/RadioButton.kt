package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material3.ext.RadioButton
import com.huanshankeji.compose.ui.Modifier

/*
https://m3.material.io/components/radio-button/overview
https://developer.android.com/develop/ui/compose/components/radio-button
 */

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
    RadioButton(selected, onClick, modifier, enabled)
