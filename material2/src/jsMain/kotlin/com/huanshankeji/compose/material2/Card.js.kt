package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.kobweb.compose.ui.modifiers.imitateComposeUiLayout
import com.varabyte.kobweb.compose.ui.toAttrs
import dev.petuska.kmdc.card.MDCCard

@Composable
actual fun Card(modifier: Modifier, content: @Composable () -> Unit) =
    MDCCard(attrs = PlatformModifier.imitateComposeUiLayout().then(modifier.platformModifier).toAttrs()) { content() }
