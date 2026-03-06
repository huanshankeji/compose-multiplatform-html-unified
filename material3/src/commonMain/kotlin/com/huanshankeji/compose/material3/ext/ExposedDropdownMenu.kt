package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.ui.Modifier

// still in the `ext` package because `content` doesn't take a `ColumnScope` receiver

// copied and adapted from `ExposedDropdownMenu.kt` in `androidx.compose.material3`

@Composable
expect fun ExposedDropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ExposedDropdownMenuBoxScope.() -> Unit,
)

expect class ExposedDropdownMenuBoxScope {
    fun Modifier.menuAnchor(): Modifier

    /*
    abstract fun Modifier.exposedDropdownSize(
        matchTextFieldWidth: Boolean = true,
    ): Modifier
    */

    /**
     * @param onDismissRequestComposeUi not supported on JS.
     * @param onCloseJsDom JS only.
     */
    @Composable
    fun ExposedDropdownMenu(
        expanded: Boolean,
        onDismissRequestComposeUi: () -> Unit,
        onCloseJsDom: () -> Unit,
        modifier: Modifier = Modifier,
        //scrollState: ScrollState = rememberScrollState(),
        matchAnchorWidthComposeUi: Boolean = true,
        content: @Composable /*ColumnScope.*/() -> Unit,
    )

    //TODO for `DropdownMenuItem`: `contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding`
}

@Composable
fun ExposedDropdownMenuBoxWithTextField(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    textFieldArgs: ExposedDropdownMenuBoxTextFieldArgs,
    //scrollState: ScrollState = rememberScrollState(),
    exposedDropdownMenuArgs: ExposedDropdownMenuArgs,
) =
    // adapted from the examples in https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)
    ExposedDropdownMenuBox(expanded, onExpandedChange, modifier) {
        ExposedDropdownMenuBoxTextField(expanded, textFieldArgs)
        with(exposedDropdownMenuArgs) {
            ExposedDropdownMenu(
                this.expanded,
                onDismissRequestComposeUi,
                onCloseJsDom,
                this.modifier,
                matchAnchorWidthComposeUi,
                content,
            )
        }
    }

/**
 * An alias of [ExposedDropdownMenuBoxWithTextField].
 */
@Composable
fun ExposedDropdownMenuBoxWithFilledTextField(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    textFieldArgs: ExposedDropdownMenuBoxTextFieldArgs,
    //scrollState: ScrollState = rememberScrollState(),
    exposedDropdownMenuArgs: ExposedDropdownMenuArgs,
) =
    ExposedDropdownMenuBoxWithTextField(expanded, onExpandedChange, modifier, textFieldArgs, exposedDropdownMenuArgs)

@Composable
fun ExposedDropdownMenuBoxWithOutlinedTextField(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    textFieldArgs: ExposedDropdownMenuBoxTextFieldArgs,
    //scrollState: ScrollState = rememberScrollState(),
    exposedDropdownMenuArgs: ExposedDropdownMenuArgs,
) =
    // adapted from the examples in https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)
    ExposedDropdownMenuBox(expanded, onExpandedChange, modifier) {
        ExposedDropdownMenuBoxOutlinedTextField(expanded, textFieldArgs)
        with(exposedDropdownMenuArgs) {
            ExposedDropdownMenu(
                this.expanded,
                onDismissRequestComposeUi,
                onCloseJsDom,
                this.modifier,
                matchAnchorWidthComposeUi,
                content,
            )
        }
    }

@ExperimentalApi
interface ICommonExposedDropdownMenuBoxTextFieldArgs {
    //val value: String
    //val onValueChange: (String) -> Unit
    val enabled: Boolean
    val label: String
    val supportingText: String?
    val isError: Boolean
}

class ExposedDropdownMenuBoxTextFieldArgs(
    val value: String,
    val onValueChange: (String) -> Unit = {}, // pass this only when specifying custom values in the text field
    override val enabled: Boolean = true,
    val readOnly: Boolean = true,
    val singleLine: Boolean = true,
    override val label: String,
    override val supportingText: String? = null,
    override val isError: Boolean = false,
) : ICommonExposedDropdownMenuBoxTextFieldArgs

@Composable
expect fun ExposedDropdownMenuBoxScope.ExposedDropdownMenuBoxTextField(
    expanded: Boolean,
    args: ExposedDropdownMenuBoxTextFieldArgs,
)
// TODO Set `menuAnchor` on the icon too when the text field is editable and `MenuAnchorType.SecondaryEditable` is supported. See https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1).

@Composable
expect fun ExposedDropdownMenuBoxScope.ExposedDropdownMenuBoxOutlinedTextField(
    expanded: Boolean,
    args: ExposedDropdownMenuBoxTextFieldArgs,
)

interface ICommonExposedDropdownMenuArgs {
    //val expanded: Boolean
    val onDismissRequestComposeUi: () -> Unit
    val onCloseJsDom: () -> Unit

    //val modifier: Modifier
    val content: @Composable /*ColumnScope.*/(() -> Unit)
}

class ExposedDropdownMenuArgs(
    // TODO Consider merging this with the `expanded` in `ExposedDropdownMenuBoxScope` to avoid potential confusion.
    val expanded: Boolean,
    override val onDismissRequestComposeUi: () -> Unit,
    override val onCloseJsDom: () -> Unit,
    val modifier: Modifier = Modifier,
    val matchAnchorWidthComposeUi: Boolean = true,
    override val content: @Composable /*ColumnScope.*/() -> Unit,
) : ICommonExposedDropdownMenuArgs
