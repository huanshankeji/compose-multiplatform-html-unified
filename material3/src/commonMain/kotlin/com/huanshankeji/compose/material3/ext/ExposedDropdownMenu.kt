package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

// still in the `ext` package because `content` doesn't take a `ColumnScope` receiver

// copied and adapted from `ExposedDropdownMenu.kt` in `androidx.compose.material3`

@Composable
expect fun ExposedDropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ExposedDropdownMenuBoxScope.() -> Unit
)

expect class ExposedDropdownMenuBoxScope {
    fun Modifier.menuAnchor(): Modifier

    /*
    abstract fun Modifier.exposedDropdownSize(
        matchTextFieldWidth: Boolean = true
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
        content: @Composable /*ColumnScope.*/() -> Unit
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
    exposedDropdownMenuArgs: ExposedDropdownMenuArgs
) =
    // adapted from the examples in https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)
    ExposedDropdownMenuBox(expanded, onExpandedChange, modifier) {
        ExposedDropdownMenuBoxTextField(expanded, textFieldArgs)
        with(exposedDropdownMenuArgs) {
            ExposedDropdownMenu(
                this.expanded, onDismissRequestComposeUi, onCloseJsDom, this.modifier, content
            )
        }
    }

class ExposedDropdownMenuBoxTextFieldArgs(
    val value: String,
    val onValueChange: (String) -> Unit = {}, // pass this only when specifying custom values in the text field
    val readOnly: Boolean = true,
    val singleLine: Boolean = true,
    val label: String
)

@Composable
expect fun ExposedDropdownMenuBoxScope.ExposedDropdownMenuBoxTextField(
    expanded: Boolean,
    args: ExposedDropdownMenuBoxTextFieldArgs
)
// TODO Set `menuAnchor` on the icon too when the text field is editable and `MenuAnchorType.SecondaryEditable` is supported. See https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1).

class ExposedDropdownMenuArgs(
    val expanded: Boolean,
    val onDismissRequestComposeUi: () -> Unit,
    val onCloseJsDom: () -> Unit,
    val modifier: Modifier = Modifier,
    val content: @Composable /*ColumnScope.*/() -> Unit
)
