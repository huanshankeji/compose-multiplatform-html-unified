package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.ColumnScope
import com.huanshankeji.compose.foundation.layout.toCommonColumnScopeContent
import com.huanshankeji.compose.ui.Modifier
import com.varabyte.kobweb.compose.foundation.layout.Column

@Composable
actual fun Card(
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit
) =
    com.huanshankeji.compose.material3.ext.Card(modifier) {
        Column(content = content.toCommonColumnScopeContent())
    }

@Composable
actual fun ElevatedCard(
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit
) =
    com.huanshankeji.compose.material3.ext.ElevatedCard(modifier) {
        Column(content = content.toCommonColumnScopeContent())
    }

@Composable
actual fun OutlinedCard(
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit
) =
    com.huanshankeji.compose.material3.ext.OutlinedCard(modifier) {
        Column(content = content.toCommonColumnScopeContent())
    }
