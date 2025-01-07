package com.huanshankeji.compose.material.demo

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.foundation.VerticalScrollBox
import com.huanshankeji.compose.foundation.background
import com.huanshankeji.compose.foundation.layout.*
import com.huanshankeji.compose.foundation.layout.ext.outerPadding
import com.huanshankeji.compose.foundation.text.KeyboardActions
import com.huanshankeji.compose.foundation.text.KeyboardOptions
import com.huanshankeji.compose.foundation.text.input.ImeAction
import com.huanshankeji.compose.foundation.text.input.KeyboardCapitalization
import com.huanshankeji.compose.foundation.text.input.KeyboardType
import com.huanshankeji.compose.material.icons.Icons
import com.huanshankeji.compose.material.icons.filled.Add
import com.huanshankeji.compose.material.icons.filled.Done
import com.huanshankeji.compose.material.icons.filled.Menu
import com.huanshankeji.compose.material.icons.filled.Search
import com.huanshankeji.compose.material2.*
import com.huanshankeji.compose.material2.ext.*
import com.huanshankeji.compose.material2.lazy.ext.List
import com.huanshankeji.compose.material2.lazy.ext.ListItemComponents
import com.huanshankeji.compose.material2.lazy.ext.conventionalItem
import com.huanshankeji.compose.material2.lazy.ext.conventionalItems
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import com.huanshankeji.compose.material2.ext.Button as ExtButton

@Composable
fun Material2(/*modifier: Modifier = Modifier*/) {
    val snackbarHostState = remember { SnackbarHostState() }
    // It seems the modifier can't be set on `TopAppBarScaffold` or a box wrapping it
    TopAppBarScaffold({
        Text("Compose Multiplatform HTML Unified demo")
    }, navigationIcon = {
        MaterialIconNavButton({}, icon = Icons.Default.Menu, contentDescription = "menu")
    }, actions = {
        MaterialIconActionButton({}, icon = Icons.Default.Search, contentDescription = "search")
        MaterialIconActionButton({}, icon = Icons.Default.Done, contentDescription = "done")
    }, bottomBar = {
        Text("Bottom bar") // The Material 2 bottom bar is not added so this is a placeholder.
    }, snackbarHost = {
        SnackbarHost(snackbarHostState)
    }, floatingActionButton = {
        Text("FAB") // The Material 2 FAB is not added so this is a placeholder.
    }) {
        VerticalScrollBox {
            Card(contentPaddingModifier) {
                Column(contentPaddingModifier.background(Color(0xF8, 0xF8, 0xF8, 0xFF)), Arrangement.spacedBy(16.dp)) {
                    Text("Material text")

                    var count by remember { mutableStateOf(0) }
                    val coroutineScope = rememberCoroutineScope()
                    val onClick: () -> Unit = {
                        count++
                        val count = count
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Count incremented to $count", "action")
                        }
                    }
                    val buttonContent: @Composable () -> Unit = {
                        TaglessText(count.toString())
                    }
                    val rowScopeButtonContent: @Composable RowScope.() -> Unit = { buttonContent() }

                    Row {
                        Button(onClick, content = rowScopeButtonContent)
                        OutlinedButton(onClick, content = rowScopeButtonContent)
                        TextButton(onClick, content = rowScopeButtonContent)
                        ExtButton(onClick) {
                            Label(count.toString())
                        }
                        IconButton(onClick, icon = Icons.Default.Add, contentDescription = "increment count")
                    }

                    val listModifier = Modifier.outerPadding(16.dp).height(listSize)
                    List(listModifier) {
                        item {
                            Text("Ungrouped item")
                        }
                        items(count) {
                            Text("Ungrouped item $it/$count")
                        }
                        group(headerContent = {
                            Text("Group title")
                        }) {
                            item {
                                Text("Grouped item")
                            }
                            items(count) {
                                Text("Grouped item $it/$count")
                            }
                        }
                    }
                    Divider()
                    val secondaryText = "Secondary text"
                    List(listModifier) {
                        conventionalItem(content = ListItemComponents("Ungrouped item", secondaryText))
                        conventionalItems(count) {
                            ListItemComponents("Ungrouped item $it/$count", secondaryText)
                        }
                        group(headerContent = {
                            Text("Group title")
                        }) {
                            conventionalItem(content = ListItemComponents("Grouped item", secondaryText))
                            conventionalItems(count) {
                                ListItemComponents("Grouped item $it/$count", secondaryText)
                            }
                        }
                    }

                    var text by remember { mutableStateOf("") }
                    TextFieldWithMaterialIcons(
                        text, { text = it },
                        label = "Demo text field",
                        leadingIcon = Icons.Default.Add,
                        trailingIcon = Icons.Default.Menu,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                    OutlinedTextFieldWithMaterialIcons(
                        text, { text = it },
                        label = "Demo text field",
                        leadingIcon = Icons.Default.Add,
                        trailingIcon = Icons.Default.Menu,
                        keyboardOptions = KeyboardOptions(
                            KeyboardCapitalization.Words, true, imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions {
                            println("keyboard actions with: $text")
                        },
                        singleLine = true
                    )
                    TextArea(text, { text = it }, label = "Demo text field", lines = 3)

                    var selected by remember { mutableStateOf(Selection.A) }
                    RadioGroupRow {
                        @Composable
                        fun RadioButtonRow(state: Selection) =
                            RadioRow(selected == state, state.toString(), { selected = state })
                        Selection.entries.forEach { RadioButtonRow(it) }
                    }

                    Row {
                        var checked by remember { mutableStateOf(false) }
                        Checkbox(checked, { checked = it })
                        Switch(checked, { checked = it })
                        SwitchWithLabel(checked, { checked = it }, "Switch")
                    }
                }
            }
        }
    }
}
