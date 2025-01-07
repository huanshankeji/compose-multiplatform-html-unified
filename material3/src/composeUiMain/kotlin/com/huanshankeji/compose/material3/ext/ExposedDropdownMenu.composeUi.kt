package com.huanshankeji.compose.material3.ext

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier

@Composable
actual fun ExposedDropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier,
    content: @Composable ExposedDropdownMenuBoxScope.() -> Unit
) =
    @OptIn(ExperimentalMaterial3Api::class)
    androidx.compose.material3.ExposedDropdownMenuBox(expanded, onExpandedChange, modifier.platformModifier) {
        ExposedDropdownMenuBoxScope(this).content()
    }

@OptIn(ExperimentalMaterial3Api::class)
actual class ExposedDropdownMenuBoxScope(val platformValue: androidx.compose.material3.ExposedDropdownMenuBoxScope) {
    actual fun Modifier.menuAnchor(): Modifier =
        platformModify { with(platformValue) { menuAnchor() } } // TODO add `type`

    @Composable
    actual fun ExposedDropdownMenu(
        expanded: Boolean,
        onDismissRequestComposeUi: () -> Unit,
        onCloseJsDom: () -> Unit,
        modifier: Modifier,
        content: @Composable () -> Unit
    ) =
        platformValue.ExposedDropdownMenu(
            expanded,
            onDismissRequestComposeUi,
            modifier.platformModifier
        ) { content() }
}


@Composable
actual fun ExposedDropdownMenuBoxScope.ExposedDropdownMenuBoxTextField(
    expanded: Boolean, args: ExposedDropdownMenuBoxTextFieldArgs
) =
    with(args) {
        @OptIn(ExperimentalMaterial3Api::class)
        TextField(
            // TODO add `type`
            modifier = with(platformValue) { PlatformModifier.menuAnchor(/*MenuAnchorType.PrimaryNotEditable*/) }, // `MenuAnchorType` seems to be not supported in the latest version of Compose Multiplatform yet
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            singleLine = singleLine,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
    }

//internal expect fun menuAnchorModifier(editable: Boolean)
