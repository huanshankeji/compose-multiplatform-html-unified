package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
expect fun Card(modifier: Modifier = Modifier, content: @Composable () -> Unit)
// `backgroundColor` may be supported. See https://github.com/material-components/material-components-web/tree/master/packages/mdc-card.
