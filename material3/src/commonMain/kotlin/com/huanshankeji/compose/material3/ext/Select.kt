package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design filled select dropdown.
 *
 * This component provides different implementations on different platforms:
 * - On Compose UI: Uses [ExposedDropdownMenuBoxWithTextField] with a filled text field
 * - On JS: Uses native Material Web `MdFilledSelect` component
 *
 * @param value the currently selected value
 * @param onValueChange called when the selected value changes
 * @param modifier the [Modifier] to be applied to this select
 * @param enabled controls the enabled state of this select
 * @param label optional label for this select
 * @param options the options to display in the dropdown, typically [SelectOption] calls
 *
 * @see <a href="https://m3.material.io/components/menus/overview">Material Design select menus</a>
 */
@Composable
expect fun FilledSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    options: @Composable () -> Unit
)

/**
 * Material Design outlined select dropdown.
 *
 * This component provides different implementations on different platforms:
 * - On Compose UI: Uses [ExposedDropdownMenuBoxWithTextField] with an outlined text field
 * - On JS: Uses native Material Web `MdOutlinedSelect` component
 *
 * @param value the currently selected value
 * @param onValueChange called when the selected value changes
 * @param modifier the [Modifier] to be applied to this select
 * @param enabled controls the enabled state of this select
 * @param label optional label for this select
 * @param options the options to display in the dropdown, typically [SelectOption] calls
 *
 * @see <a href="https://m3.material.io/components/menus/overview">Material Design select menus</a>
 */
@Composable
expect fun OutlinedSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    options: @Composable () -> Unit
)

/**
 * Material Design select option.
 *
 * Should be used within [FilledSelect] or [OutlinedSelect].
 *
 * @param value the value of this option
 * @param text the displayed text for this option
 * @param modifier the [Modifier] to be applied to this option
 */
@Composable
expect fun SelectOption(
    value: String,
    text: String,
    modifier: Modifier = Modifier
)
