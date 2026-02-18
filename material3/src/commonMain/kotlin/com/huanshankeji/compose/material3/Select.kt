package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design filled select dropdown.
 *
 * Note: The API differs significantly between platforms.
 * - On Compose UI: Uses ExposedDropdownMenuBox
 * - On JS: Uses native MdFilledSelect
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
 * Note: The API differs significantly between platforms.
 * - On Compose UI: Uses ExposedDropdownMenuBox with OutlinedTextField
 * - On JS: Uses native MdOutlinedSelect
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
 * Should be used within FilledSelect or OutlinedSelect.
 */
@Composable
expect fun SelectOption(
    value: String,
    text: String,
    modifier: Modifier = Modifier
)
