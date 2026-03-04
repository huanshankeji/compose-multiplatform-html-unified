package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.foundation.layout.ColumnScope
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.unit.ext.DpOrPercentage

/*
https://developer.android.com/develop/ui/compose/components/radio-button
 */

/**
 * Material Design radio button.
 *
 * @see <a href="https://m3.material.io/components/radio-button/overview">Material Design radio button</a>
 * @see androidx.compose.material3.RadioButton
 */
@Composable
expect fun RadioButton(
    idJsDom: String? = null,
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
)

@Composable
expect fun RadioButtonRow(
    radioButtonIdJsDom: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    radioButtonModifier: Modifier = Modifier,
    conventionalPadding: Dp? = 16.dp,
    //labelModifier
    enabled: Boolean = true,
    content: @Composable () -> Unit,
)

/*
@Composable
expect fun RadioGroupRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit)

@Composable
expect fun RadioGroupColumn(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit)
*/

expect fun Modifier.radioGroup() : Modifier
