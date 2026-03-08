package com.huanshankeji.compose.material.demo

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.huanshankeji.androidx.lifecycle.viewmodel.compose.viewModel
import com.huanshankeji.compose.ExtRecommendedApi
import com.huanshankeji.compose.foundation.background
import com.huanshankeji.compose.foundation.layout.*
import com.huanshankeji.compose.foundation.layout.ext.fillMaxSizeStretch
import com.huanshankeji.compose.foundation.rememberScrollState
import com.huanshankeji.compose.foundation.text.KeyboardActions
import com.huanshankeji.compose.foundation.text.KeyboardOptions
import com.huanshankeji.compose.foundation.text.input.ImeAction
import com.huanshankeji.compose.foundation.text.input.KeyboardCapitalization
import com.huanshankeji.compose.foundation.text.input.KeyboardType
import com.huanshankeji.compose.foundation.verticalScroll
import com.huanshankeji.compose.material.icons.Icons
import com.huanshankeji.compose.material.icons.filled.*
import com.huanshankeji.compose.material3.*
import com.huanshankeji.compose.material3.ext.*
import com.huanshankeji.compose.material3.ext.Card
import com.huanshankeji.compose.material3.ext.ElevatedCard
import com.huanshankeji.compose.material3.ext.OutlinedCard
import com.huanshankeji.compose.material3.lazy.ext.List
import com.huanshankeji.compose.material3.lazy.ext.ListItemComponents
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import com.huanshankeji.compose.material3.Button as RowScopeButton

@Composable
fun Material3(
    /*modifier: Modifier = Modifier*/
    viewModel: Material3ViewModel = viewModel { Material3ViewModel() },
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Box(Modifier.fillMaxSizeStretch()) {
        Column(Modifier.verticalScroll(rememberScrollState()).innerContentPadding(), Arrangement.spacedBy(16.dp)) {
            val count by viewModel.countState.collectAsState()
            val onClick: () -> Unit = {
                val newCount = ++viewModel.countState.value
                scope.launch {
                    snackbarHostState.showSnackbar("Count incremented to $newCount")
                }
            }
            val buttonContent: @Composable () -> Unit = {
                TaglessText(count.toString())
            }
            val rowScopeButtonContent: @Composable RowScope.() -> Unit = { buttonContent() }
            Row {
                RowScopeButton(onClick, content = rowScopeButtonContent)
                Button(onClick, content = buttonContent)
                ElevatedButton(onClick, content = buttonContent)
                FilledTonalButton(onClick, content = buttonContent)
                OutlinedButton(onClick, content = buttonContent)
                TextButton(onClick, content = buttonContent)
            }
            Row {
                ButtonWithMaterialIcon(onClick, icon = Icons.Default.Add, content = buttonContent)
                ButtonWithMaterialIcon(
                    onClick,
                    icon = Icons.Default.Add,
                    isTrailingIcon = true,
                    content = buttonContent
                )
            }
            val iconButtonContent: @Composable () -> Unit = {
                Icon(Icons.Default.Add, null)
            }
            Row {
                IconButton(onClick, content = iconButtonContent)
                FilledIconButton(onClick, content = iconButtonContent)
                FilledTonalIconButton(onClick, content = iconButtonContent)
                OutlinedIconButton(onClick, content = iconButtonContent)
            }
            val checked = viewModel.checkedState.collectAsState().value
            val onCheckedChange: (Boolean) -> Unit = { viewModel.checkedState.value = it }
            val iconToggleButtonContent: @Composable () -> Unit = {
                Icon(if (checked) Icons.Default.Add else Icons.Default.Remove, null)
            }
            @OptIn(ExtRecommendedApi::class)
            Row {
                IconToggleButton(checked, onCheckedChange, content = iconToggleButtonContent)
                FilledIconToggleButton(checked, onCheckedChange, content = iconToggleButtonContent)
                FilledTonalIconToggleButton(checked, onCheckedChange, content = iconToggleButtonContent)
                OutlinedIconToggleButton(checked, onCheckedChange, content = iconToggleButtonContent)
            }
            Row {
                IconToggleButtonWithMaterialIcons(
                    checked, onCheckedChange,
                    uncheckedIcon = Icons.Default.Remove, checkedIcon = Icons.Default.Add,
                )
                FilledIconToggleButtonWithMaterialIcons(
                    checked, onCheckedChange,
                    uncheckedIcon = Icons.Default.Remove, checkedIcon = Icons.Default.Add,
                )
                FilledTonalIconToggleButtonWithMaterialIcons(
                    checked, onCheckedChange,
                    uncheckedIcon = Icons.Default.Remove, checkedIcon = Icons.Default.Add,
                )
                OutlinedIconToggleButtonWithMaterialIcons(
                    checked, onCheckedChange,
                    uncheckedIcon = Icons.Default.Remove, checkedIcon = Icons.Default.Add,
                )
            }
            @OptIn(ExtRecommendedApi::class)
            Row {
                FloatingActionButton(onClick, content = iconButtonContent)
                SmallFloatingActionButton(onClick, content = iconButtonContent)
                LargeFloatingActionButton(onClick, content = iconButtonContent)
                ExtendedFloatingActionButton(onClick) {
                    iconButtonContent()
                    Text("Add")
                }
            }
            Row {
                FloatingActionButtonWithMaterialIcon(onClick, icon = Icons.Default.Add)
                SmallFloatingActionButtonWithMaterialIcon(onClick, icon = Icons.Default.Add)
                LargeFloatingActionButtonWithMaterialIcon(onClick, icon = Icons.Default.Add)
                ExtendedFloatingActionButtonWithMaterialIcon(onClick, label = "Add", icon = Icons.Default.Add)
            }
            Row {
                Checkbox(checked, onCheckedChange)
                Switch(checked, onCheckedChange)
            }

            var text by remember { mutableStateOf("") }
            val label = "label"
            val placeholder = "placeholder"
            val prefix = "prefix"
            val suffix = "suffix"
            val supportingText = "supporting text"
            TextFieldWithMaterialIcons(
                text,
                { text = it },
                label = label,
                placeholder = placeholder,
                leadingIcon = Icons.Default.Add,
                trailingIcon = Icons.Default.Menu,
                prefix = prefix,
                suffix = suffix,
                supportingText = supportingText,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            OutlinedTextFieldWithMaterialIcons(
                text,
                { text = it },
                label = label,
                placeholder = placeholder,
                leadingIcon = Icons.Default.Add,
                trailingIcon = Icons.Default.Menu,
                prefix = prefix,
                suffix = suffix,
                supportingText = supportingText,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Words, true, imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions {
                    println("keyboard actions with: $text")
                },
            )
            OutlinedTextField(text, { text = it }, label = label, placeholder = placeholder, lines = 2)

            Text("Click a button to show the list:")
            List(Modifier.height(listSize)) {
                fun content(index: String) =
                    ListItemComponents(
                        Modifier,
                        true,
                        "Headline $index",
                        Icons.Default.Add,
                        Icons.Default.Menu,
                        "Supporting text $index",
                        "Trailing supporting text $index",
                        "Overline $index",
                    )
                conventionalItem(content = content(""))
                conventionalItems(count) { index ->
                    content(index.toString())
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Card { Text("card", contentPaddingModifier) }
                ElevatedCard { Text("elevated card", contentPaddingModifier) }
                OutlinedCard { Text("outlined card", contentPaddingModifier) }
            }

            var selectedIndex by remember { mutableStateOf(0) }
            NavigationBar {
                NavigationBarItemWithMaterialIcons(
                    selectedIndex == 0,
                    { selectedIndex = 0 },
                    Icons.Default.Add,
                    label = "Add",
                )
                NavigationBarItemWithMaterialIcons(
                    selectedIndex == 1,
                    { selectedIndex = 1 },
                    Icons.Default.Remove,
                    label = "Remove",
                )
            }

            @Composable
            fun DropdownMenuContent(setSelection: (Selection?) -> Unit, close: () -> Unit) =
                (listOf(null) + Selection.entries).forEach {
                    DropdownMenuItemWithMaterialIcons(
                        { modifier -> it?.let { Text(it.displayText(), modifier) } },
                        {
                            setSelection(it)
                            close()
                        },
                        leadingIcon = Icons.Filled.Add,
                        trailingIcon = Icons.Filled.Remove,
                    )
                }

            fun Selection?.valueJsDom(): String =
                this?.name?.lowercase() ?: ""

            @Composable
            fun SelectMenuContent(setSelection: (Selection?) -> Unit, close: () -> Unit) =
                (listOf(null) + Selection.entries).forEach {
                    SelectOptionWithMaterialIcons(
                        { modifier -> it?.let { Text(it.displayText(), modifier) } },
                        {
                            setSelection(it)
                            close()
                        },
                        it.valueJsDom(),
                        leadingIcon = Icons.Filled.Add,
                        trailingIcon = Icons.Filled.Remove,
                    )
                }


            run {
                val (selection, setSelection) = remember { mutableStateOf<Selection?>(null) }
                val value = selection?.displayText() ?: ""
                Text("Selected: $value")
                val label = "Please select"
                run {
                    val (expanded, setExpanded) = remember { mutableStateOf(false) }
                    val close = { setExpanded(false) }
                    ExposedDropdownMenuBoxWithTextField(
                        expanded, setExpanded,
                        textFieldArgs = ExposedDropdownMenuBoxTextFieldArgs(value, label = label),
                        exposedDropdownMenuArgs = ExposedDropdownMenuArgs(expanded, close, close) {
                            DropdownMenuContent(setSelection, close)
                        },
                    )
                }
                run {
                    val (expanded, setExpanded) = remember { mutableStateOf(false) }
                    val close = { setExpanded(false) }
                    ExposedDropdownMenuBoxWithOutlinedTextField(
                        expanded, setExpanded,
                        textFieldArgs = ExposedDropdownMenuBoxTextFieldArgs(value, label = label),
                        exposedDropdownMenuArgs = ExposedDropdownMenuArgs(expanded, close, close) {
                            DropdownMenuContent(setSelection, close)
                        },
                    )
                }

                // `valueJsDom` doesn't necessarily need to be the same as `value`. See its KDoc for details.
                val valueJsDom = selection?.name ?: ""
                // an alternative using `ordinal`
                //val valueJsDom = selection?.run { ordinal.toString() } ?: ""
                run {
                    val (expanded, setExpanded) = remember { mutableStateOf(false) }
                    val close = { setExpanded(false) }
                    FilledSelect(
                        expanded, setExpanded,
                        valueJsDom,
                        textFieldArgs = SelectTextFieldArgs(value, label = label),
                        menuArgs = SelectMenuArgs(expanded, close, close) {
                            SelectMenuContent(setSelection, close)
                        },
                    )
                }
                run {
                    val (expanded, setExpanded) = remember { mutableStateOf(false) }
                    val close = { setExpanded(false) }
                    OutlinedSelect(
                        expanded, setExpanded,
                        valueJsDom,
                        textFieldArgs = SelectTextFieldArgs(value, label = label),
                        menuArgs = SelectMenuArgs(expanded, close, close) {
                            SelectMenuContent(setSelection, close)
                        },
                    )
                }

                DropdownMenuBox {
                    var expanded by remember { mutableStateOf(false) }
                    val close = { expanded = false }
                    IconButton({ expanded = true }, Modifier.menuAnchorJsDom()) {
                        Icon(Icons.Filled.ArrowDropDown, "Please select")
                    }
                    DropdownMenu(expanded, close, close) {
                        DropdownMenuContent(setSelection, close)
                    }
                }
            }

            LinearProgressIndicator()
            LinearProgressIndicator({ 0.5f })
            CircularProgressIndicator()
            CircularProgressIndicator({ 0.5f })

            // RadioButton
            var radioSelection by remember { mutableStateOf(Selection.A) }

            @Composable
            fun RadioGroupContent() {
                Selection.entries.forEach { selection ->
                    RadioButtonRow(
                        radioSelection == selection,
                        { radioSelection = selection },
                        enabled = selection != Selection.C,
                        radioButtonIdJsDom = selection.name.lowercase(),
                    ) {
                        TaglessText(selection.displayText())
                    }
                }
            }

            // This is only for the demo. Avoid using `Row`. See https://m3.material.io/components/radio-button/guidelines for details.
            Row(Modifier.radioGroup(), horizontalArrangement = Arrangement.spacedBy(16.dp)) { RadioGroupContent() }
            Column(Modifier.radioGroup(), verticalArrangement = Arrangement.spacedBy(16.dp)) { RadioGroupContent() }

            // HorizontalDivider
            HorizontalDivider()

            // Slider
            Column {
                var sliderPosition by remember { mutableStateOf(0.5f) }
                Text("Slider position: $sliderPosition")
                var sliderChanging by remember { mutableStateOf(false) }
                Text("Slider changing: $sliderChanging")
                Slider(
                    value = sliderPosition,
                    onValueChange = {
                        sliderChanging = true
                        sliderPosition = it
                    },
                    onValueChangeFinished = {
                        sliderChanging = false
                    },
                )
                var discreteSliderPosition by remember { mutableStateOf(0.5f) }
                Text("Discrete slider position: $discreteSliderPosition")
                Slider(
                    value = discreteSliderPosition,
                    onValueChange = { discreteSliderPosition = it },
                    steps = 3,
                )
                var rangeSliderPosition by remember { mutableStateOf(0.25f..0.75f) }
                Text("Range slider position: $rangeSliderPosition")
                RangeSlider(
                    value = rangeSliderPosition,
                    onValueChange = { rangeSliderPosition = it },
                )
            }

            // Badge
            Row {
                // not shown at the right position
                Badge(content = "3")
                Badge(content = "New")
            }

            // Chips - showing all types as per M3 design
            var filterChipSelected by remember { mutableStateOf(false) }
            var inputChipSelected by remember { mutableStateOf(false) }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                AssistChip(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("Assist chip clicked") } },
                    label = "Assist",
                    leadingIcon = { modifier -> Icon(Icons.Default.Add, null, modifier) },
                )
                FilterChip(
                    selected = filterChipSelected,
                    onClick = { filterChipSelected = !filterChipSelected },
                    label = "Filter",
                    leadingIcon = if (filterChipSelected) { modifier ->
                        Icon(
                            Icons.Filled.Done,
                            null,
                            modifier
                        )
                    } else null,
                )
                var showInputChip by remember { mutableStateOf(true) }
                if (showInputChip)
                    InputChip(
                        selected = inputChipSelected,
                        onClick = { inputChipSelected = !inputChipSelected },
                        label = "Input",
                        avatar = { modifier -> Icon(Icons.Default.Person, null, modifier) },
                        onRemove = {
                            showInputChip = false
                            scope.launch { snackbarHostState.showSnackbar("Input chip removed") }
                        },
                    )
                SuggestionChip(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("Suggestion chip clicked") } },
                    label = "Suggestion",
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ElevatedAssistChip(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("Elevated assist chip clicked") } },
                    label = "Elevated Assist",
                    leadingIcon = { modifier -> Icon(Icons.Default.Add, null, modifier) },
                )
                ElevatedFilterChip(
                    selected = filterChipSelected,
                    onClick = { filterChipSelected = !filterChipSelected },
                    label = "Elevated Filter",
                    leadingIcon = if (filterChipSelected) { modifier ->
                        Icon(
                            Icons.Filled.Done,
                            null,
                            modifier
                        )
                    } else null,
                )
                ElevatedSuggestionChip(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("Elevated suggestion chip clicked") } },
                    label = "Elevated Suggestion",
                )
            }

            // Dialog
            var isAlertDialogOpen by remember { mutableStateOf(false) }
            Button(onClick = { isAlertDialogOpen = true }) {
                Text("Show Alert Dialog")
            }
            AlertDialog(
                isAlertDialogOpen,
                { isAlertDialogOpen = false },
                {
                    Button(onClick = { isAlertDialogOpen = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { isAlertDialogOpen = false }) {
                        Text("Cancel")
                    }
                },
                icon = { Icon(Icons.Default.Add, null, it) },
                title = { Text("Alert Dialog", it) },
                text = { Text("This is a demo alert dialog with headline, content, and action buttons.", it) },
            )
            var isSimpleDialogOpen by remember { mutableStateOf(false) }
            Button(onClick = { isSimpleDialogOpen = true }) {
                Text("Show Dialog")
            }
            SimpleDialog(isSimpleDialogOpen, { isSimpleDialogOpen = false }) {
                TaglessText("Dialog")
            }

            // Tabs
            var selectedTabIndex by remember { mutableStateOf(0) }
            PrimaryTabRow(selectedTabIndex) {
                PrimaryTab(
                    selectedTabIndex == 0,
                    { selectedTabIndex = 0 },
                    text = { Text("Tab 1") },
                )
                PrimaryTab(
                    selectedTabIndex == 1,
                    { selectedTabIndex = 1 },
                    text = { Text("Tab 2") },
                    icon = { modifier -> Icon(Icons.Default.Add, null, modifier) },
                )
                PrimaryTab(
                    selectedTabIndex == 2,
                    { selectedTabIndex = 2 },
                    text = { Text("Tab 3") },
                )
            }
            SecondaryTabRow(selectedTabIndex) {
                SecondaryTab(
                    selectedTabIndex == 0,
                    { selectedTabIndex = 0 },
                    text = { Text("Tab 1") },
                )
                SecondaryTab(
                    selectedTabIndex == 1,
                    { selectedTabIndex = 1 },
                    text = { Text("Tab 2") },
                    icon = { modifier -> Icon(Icons.Default.Add, null, modifier) },
                )
                SecondaryTab(
                    selectedTabIndex == 2,
                    { selectedTabIndex = 2 },
                    text = { Text("Tab 3") },
                )
            }
            Text("Selected tab: ${selectedTabIndex + 1}", Modifier.padding(16.dp))

            // Segmented Buttons - Single select with Selection enum
            var selectedSegment by remember { mutableStateOf(Selection.A) }
            SingleChoiceSegmentedButtonRow {
                Selection.entries.forEachIndexed { index, selection ->
                    SegmentedButton(
                        selectedSegment == selection,
                        { selectedSegment = selection },
                        SegmentedButtonDefaultShapeArgs(index, Selection.entries.size),
                        label = selection.displayText(),
                    )
                }
            }

            // Segmented Buttons - Multi-select
            val isIndexSelected = remember { mutableStateListOf(false, false, false) }
            MultiChoiceSegmentedButtonRow {
                Selection.entries.forEachIndexed { index, selection ->
                    SegmentedButton(
                        isIndexSelected[index],
                        { isIndexSelected[index] = it },
                        SegmentedButtonDefaultShapeArgs(index, Selection.entries.size),
                        label = selection.displayText(),
                    )
                }
            }

            // TopAppBar demos
            Text("Top App Bars:")
            TopAppBar(
                title = { Text("Small") },
                navigationIcon = {
                    IconButton({ scope.launch { snackbarHostState.showSnackbar("Nav clicked") } }) {
                        Icon(
                            Icons.Default.Menu,
                            null
                        )
                    }
                },
                actions = {
                    IconButton({ scope.launch { snackbarHostState.showSnackbar("Search clicked") } }) {
                        Icon(
                            Icons.Default.Search,
                            null
                        )
                    }
                },
            )
            CenterAlignedTopAppBar(
                title = { Text("Center Aligned") },
                navigationIcon = {
                    IconButton({ scope.launch { snackbarHostState.showSnackbar("Nav clicked") } }) {
                        Icon(
                            Icons.Default.Menu,
                            null
                        )
                    }
                },
                actions = {
                    IconButton({ scope.launch { snackbarHostState.showSnackbar("Search clicked") } }) {
                        Icon(
                            Icons.Default.Search,
                            null
                        )
                    }
                },
            )
            MediumTopAppBar(
                title = { Text("Medium") },
                navigationIcon = {
                    IconButton({ scope.launch { snackbarHostState.showSnackbar("Nav clicked") } }) {
                        Icon(
                            Icons.Default.Menu,
                            null
                        )
                    }
                },
                actions = {
                    IconButton({ scope.launch { snackbarHostState.showSnackbar("Search clicked") } }) {
                        Icon(
                            Icons.Default.Search,
                            null
                        )
                    }
                },
            )
            LargeTopAppBar(
                title = { Text("Large") },
                navigationIcon = {
                    IconButton({ scope.launch { snackbarHostState.showSnackbar("Nav clicked") } }) {
                        Icon(
                            Icons.Default.Menu,
                            null
                        )
                    }
                },
                actions = {
                    IconButton({ scope.launch { snackbarHostState.showSnackbar("Search clicked") } }) {
                        Icon(
                            Icons.Default.Search,
                            null
                        )
                    }
                },
            )

            // Snackbar demo
            Button(onClick = { scope.launch { snackbarHostState.showSnackbar("This is a snackbar") } }) {
                Text("Show Snackbar")
            }
            Button(onClick = {
                scope.launch {
                    val result = snackbarHostState.showSnackbar("Snackbar with action", actionLabel = "Undo")
                    if (result == SnackbarResult.ActionPerformed)
                        snackbarHostState.showSnackbar("Action performed")
                }
            }) {
                Text("Show Snackbar with Action")
            }

            // NavigationDrawer (simplified demo)
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            Button(onClick = { scope.launch { drawerState.open() } }) {
                Text("Open Drawer")
            }
            ModalNavigationDrawer(
                {
                    // copied and adapted from https://developer.android.com/develop/ui/compose/components/drawer#example
                    ModalDrawerSheet {
                        Text("Drawer title", modifier = Modifier.padding(16.dp))
                        HorizontalDivider()
                        /*
                        NavigationDrawerItem(
                            label = { Text(text = "Drawer Item") },
                            selected = false,
                            onClick = { TODO },
                        )
                        */
                        // ...other drawer items
                        Button(onClick = { scope.launch { drawerState.close() } }) {
                            Text("Close")
                        }
                    }
                },
                drawerState = drawerState,
            ) {
                Text("Main Content", Modifier.fillMaxWidth().height(160.dp).background(Color.Gray))
            }
        }
        SnackbarHost(snackbarHostState, Modifier.align(Alignment.BottomCenter))
    }
}
