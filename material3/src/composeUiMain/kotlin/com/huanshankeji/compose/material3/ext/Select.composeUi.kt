package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.*
import com.huanshankeji.compose.material3.DropdownMenuItem
import com.huanshankeji.compose.material3.Text
import com.huanshankeji.compose.material3.TextField
import com.huanshankeji.compose.material3.OutlinedTextField
import com.huanshankeji.compose.ui.Modifier

private class SelectOptionsCollector {
    val options = mutableListOf<Pair<String, String>>() // value to text
    
    @Composable
    fun collectOption(value: String, text: String) {
        options.add(value to text)
    }
}

@Composable
actual fun FilledSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    options: @Composable () -> Unit
) {
    val collector = remember { SelectOptionsCollector() }
    collector.options.clear()
    
    // Collect options
    val composition = rememberCompositionContext()
    SideEffect {
        // Options will be collected during composition
    }
    
    var expanded by remember { mutableStateOf(false) }
    
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            label = label?.let { { Text(it) } },
            modifier = Modifier.menuAnchor()
        )
        
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequestComposeUi = { expanded = false },
            onCloseJsDom = { expanded = false }
        ) {
            // Render collected options
            options()
        }
    }
}

@Composable
actual fun OutlinedSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    options: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            label = label?.let { { Text(it) } },
            modifier = Modifier.menuAnchor()
        )
        
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequestComposeUi = { expanded = false },
            onCloseJsDom = { expanded = false }
        ) {
            options()
        }
    }
}

@Composable
actual fun SelectOption(
    value: String,
    text: String,
    modifier: Modifier
) {
    DropdownMenuItem(
        text = { Text(text) },
        onClick = { /* TODO: handle selection */ },
        modifier = modifier
    )
}
