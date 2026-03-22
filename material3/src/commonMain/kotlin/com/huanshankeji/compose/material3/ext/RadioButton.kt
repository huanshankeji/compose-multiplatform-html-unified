package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Column
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.ui.Modifier

/*
https://developer.android.com/develop/ui/compose/components/radio-button
https://developer.android.com/develop/ui/compose/components/radio-button#create-basic
 */

/**
 * Material Design radio button.
 *
 * @see <a href="https://m3.material.io/components/radio-button/overview">Material Design radio button</a>
 * @see androidx.compose.material3.RadioButton
 */
@Composable
expect fun RadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    idJsDom: String? = null,
)

/**
 * A conventional row with a radio button and its label.
 * Compose UI doesn't provide this Component officially
 * and [Material Design's official size specs](https://m3.material.io/components/radio-button/specs) are a bit vague on this.
 * Therefore, this component is designed for conventional use
 * following a mixture of conventions observed from https://github.com/material-components/material-web/blob/main/docs/components/radio.md#accessibility
 * and https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-radio-button.html.
 * By default, this row only has spacing between the radio button and the label, and has no padding around.
 */
@Composable
expect fun RadioButtonRow(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    modifierAfterSelectable: Modifier = Modifier,
    radioButtonModifier: Modifier = Modifier,
    //labelModifier
    enabled: Boolean = true,
    radioButtonIdJsDom: String,
    label: @Composable () -> Unit,
)

/**
 * By convention, pass this to a [Column].
 * Avoid using [Row]. See https://m3.material.io/components/radio-button/guidelines for details.
 */
expect fun Modifier.radioGroup(): Modifier

/*
@Composable
fun RadioGroupRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit)

@Composable
fun RadioGroupColumn(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit)
*/
