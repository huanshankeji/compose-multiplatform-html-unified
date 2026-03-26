package com.huanshankeji.compose.material.demo

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huanshankeji.compose.foundation.layout.Column
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.foundation.layout.ext.VerticalScrollColumn
import com.huanshankeji.compose.foundation.layout.ext.fillMaxSizeStretch
import com.huanshankeji.compose.foundation.layout.ext.fillMaxWidthStretch
import com.huanshankeji.compose.foundation.layout.ext.innerPadding
import com.huanshankeji.compose.foundation.layout.width
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material.icons.Icons
import com.huanshankeji.compose.material.icons.filled.Close
import com.huanshankeji.compose.material.icons.filled.Search
import com.huanshankeji.compose.material3.*
import com.huanshankeji.compose.material3.ext.OutlinedTextField
import com.huanshankeji.compose.ui.Alignment
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.style.TextAlign

@Composable
private fun IconItem(name: String, icon: Icon) {
    Column(
        Modifier.width(80.dp).innerContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(icon, name)
        Text(name, modifier = Modifier.fillMaxWidthStretch(), textAlign = TextAlign.Center)
    }
}

// TODO: use a flow layout when it's supported (#125)
private const val ICONS_PER_ROW = 8

@Composable
private fun IconSection(title: String, icons: List<Pair<String, Icon>>, searchQuery: String) {
    // There is a lagging issue on JS DOM here. See #127 for more details. `remember` `derivedStateOf` seems to have slightly improved performance but the issue still persists as tested.
    val rows by remember(searchQuery, icons) {
        derivedStateOf {
            val filtered =
                if (searchQuery.isBlank()) icons
                else icons.filter { it.first.contains(searchQuery, ignoreCase = true) }
            filtered.chunked(ICONS_PER_ROW)
        }
    }
    if (rows.isNotEmpty()) {
        // h2-like styling: browser default h2 is ~1.5em (24px) and bold
        Text(title, modifier = contentPaddingModifier, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        for (row in rows)
            Row {
                for ((name, icon) in row)
                    IconItem(name, icon)
            }
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
            IconSection(
                "`material-icons-extended` `AutoMirrored.Filled`",
                extendedOnlyAutoMirroredFilledIconNamePairs,
                searchQuery
            )
        }
    }
}
