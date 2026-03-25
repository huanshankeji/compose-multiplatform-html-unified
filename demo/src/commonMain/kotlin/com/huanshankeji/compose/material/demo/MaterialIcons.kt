package com.huanshankeji.compose.material.demo

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.huanshankeji.compose.foundation.layout.Column
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.foundation.layout.ext.VerticalScrollColumn
import com.huanshankeji.compose.foundation.layout.ext.fillMaxSizeStretch
import com.huanshankeji.compose.foundation.layout.ext.fillMaxWidthStretch
import com.huanshankeji.compose.foundation.layout.ext.innerPadding
import com.huanshankeji.compose.foundation.layout.width
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material.icons.Icons
import com.huanshankeji.compose.material.icons.filled.*
import com.huanshankeji.compose.material3.*
import com.huanshankeji.compose.material3.ext.OutlinedTextField
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier

// TODO: center text below icons after `textAlign` is supported (#23)
@Composable
private fun IconItem(name: String, icon: Icon) {
    Column(
        Modifier.width(80.dp).innerContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(icon, name)
        Text(name, modifier = Modifier.fillMaxWidthStretch())
    }
}

// TODO: use a flow layout when it's supported (#125)
private const val ICONS_PER_ROW = 16

@Composable
private fun IconGrid(icons: List<Pair<String, Icon>>, searchQuery: String) {
    val filtered = if (searchQuery.isBlank()) icons
    else icons.filter { it.first.contains(searchQuery, ignoreCase = true) }
    val chunked = filtered.chunked(ICONS_PER_ROW)
    for (row in chunked)
        Row {
            for ((name, icon) in row)
                IconItem(name, icon)
        }
}

// TODO: make section titles bigger after text styling is supported (#23)
@Composable
private fun IconSection(title: String, icons: List<Pair<String, Icon>>, searchQuery: String) {
    val filtered = if (searchQuery.isBlank()) icons
    else icons.filter { it.first.contains(searchQuery, ignoreCase = true) }
    if (filtered.isNotEmpty()) {
        Text(title, modifier = contentPaddingModifier)
        IconGrid(icons, searchQuery)
    }
}

@Composable
fun MaterialIcons() {
    var searchQuery by remember { mutableStateOf("") }
    Scaffold(
        Modifier.fillMaxSizeStretch(),
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.fillMaxWidthStretch(),
                        label = "Search icons",
                        leadingIcon = { modifier -> Icon(Icons.Filled.Search, null, modifier) },
                        trailingIcon = if (searchQuery.isNotEmpty()) {
                            { modifier ->
                                IconButton(onClick = { searchQuery = "" }, modifier = modifier) {
                                    Icon(Icons.Filled.Close, "Clear")
                                }
                            }
                        } else null,
                    )
                },
            )
        },
    ) { paddingValues ->
        VerticalScrollColumn(Modifier.fillMaxSizeStretch().innerPadding(paddingValues)) {
            IconSection("`material-icons-core` `Filled`", coreFilledIconNamePairs, searchQuery)
            IconSection("`material-icons-core` `AutoMirrored.Filled`", coreAutoMirroredFilledIconNamePairs, searchQuery)
            IconSection("`material-icons-extended` `Filled`", extendedOnlyFilledIconNamePairs, searchQuery)
            IconSection("`material-icons-extended` `AutoMirrored.Filled`", extendedOnlyAutoMirroredFilledIconNamePairs, searchQuery)
        }
    }
}
