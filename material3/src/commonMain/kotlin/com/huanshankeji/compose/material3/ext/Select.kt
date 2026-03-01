package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

@ExperimentalApi
class SelectTextFieldArgs(
    val valueComposeUi: String,
    val onValueChangeComposeUi: (String) -> Unit = {},
    override val enabled: Boolean = true,
    override val label: String,
    //noAsterisk : Boolean = false, // not supported directly on Compose UI
    override val supportingText: String? = null,
    override val isError: Boolean = false,
) : ICommonExposedDropdownMenuBoxTextFieldArgs

@ExperimentalApi
class SelectMenuArgs(
    // TODO Consider merging this with the `expanded` in `ExposedDropdownMenuBoxScope` to avoid potential confusion.
    val expandedComposeUi: Boolean,
    override val onDismissRequestComposeUi: () -> Unit,
    override val onCloseJsDom: () -> Unit,
    val matchAnchorWidth: Boolean? = null,
    override val content: @Composable /*ColumnScope.*/() -> Unit
) : ICommonExposedDropdownMenuArgs

/**
 * Material Design filled select.
 *
 * This component can be seen as a variant of [ExposedDropdownMenuBoxWithTextField] with [ExposedDropdownMenuBoxTextFieldArgs.readOnly] set to true only.
 *
 * Compared to [ExposedDropdownMenuBoxWithTextField], this component provides different implementations on different platforms:
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
 *
 * @see ExposedDropdownMenuBoxWithTextField
 */
@ExperimentalApi
@Composable
expect fun FilledSelect(
    expandedComposeUi: Boolean,
    onExpandedChangeComposeUi: (Boolean) -> Unit,
    /*
    These 2 params are temporarily not supported because their API style differ too much from Compose.
    Also note that the `value` here comes from `MdFilledSelect` and serves as the key,
    which differs from `value` in `ExposedDropdownMenuBoxTextFieldArgs` that serves as the text field value.
    */
    /*
    valueJsDom: String,
    onValueChangeJsDom: (String) -> Unit,
    */
    modifier: Modifier = Modifier,
    textFieldArgs: SelectTextFieldArgs,
    //scrollState: ScrollState = rememberScrollState(),
    menuArgs: SelectMenuArgs
)

/**
 * The outlined variant of [FilledSelect].
 */
@ExperimentalApi
@Composable
expect fun OutlinedSelect(
    expandedComposeUi: Boolean,
    onExpandedChangeComposeUi: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    textFieldArgs: SelectTextFieldArgs,
    //scrollState: ScrollState = rememberScrollState(),
    menuArgs: SelectMenuArgs
)

/**
 * Material Design select option.
 *
 * Should be used within [FilledSelect] or [OutlinedSelect].
 *
 * @see DropdownMenuItem
 * @param text corresponds to the `headline` slot on JS DOM in Material Web.
 * @param valueJsDom the `value` attribute for an HTML form.
 */
@Composable
expect fun SelectOption(
    text: @Composable (Modifier) -> Unit,
    onClick: () -> Unit,
    selectedJsDom: Boolean,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable ((Modifier) -> Unit)? = null,
    trailingIcon: @Composable ((Modifier) -> Unit)? = null,
    enabled: Boolean = true,
    valueJsDom: String? = null,
    //keepOpenJsDom: Boolean = false
)

@Composable
fun SelectOptionWithMaterialIcons(
    text: @Composable (Modifier) -> Unit,
    onClick: () -> Unit,
    selectedJsDom: Boolean,
    modifier: Modifier = Modifier,
    leadingIcon: Icon? = null,
    trailingIcon: Icon? = null,
    enabled: Boolean = true
) =
    SelectOption(
        text,
        onClick,
        selectedJsDom,
        modifier,
        leadingIcon.toNullableContentWithModifier(),
        trailingIcon.toNullableContentWithModifier(),
        enabled
    )
