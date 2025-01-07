package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.ColumnScope
import com.huanshankeji.compose.foundation.layout.toCommonColumnScopeContent
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Card(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) =
    androidx.compose.material3.Card(modifier.platformModifier, content = content.toCommonColumnScopeContent())

@Composable
actual fun ElevatedCard(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) =
    androidx.compose.material3.ElevatedCard(modifier.platformModifier, content = content.toCommonColumnScopeContent())

@Composable
actual fun OutlinedCard(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) =
    androidx.compose.material3.OutlinedCard(modifier.platformModifier, content = content.toCommonColumnScopeContent())
