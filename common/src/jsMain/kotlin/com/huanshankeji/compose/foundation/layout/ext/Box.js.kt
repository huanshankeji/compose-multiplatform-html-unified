package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.AddKobwebComposeStyleSheet
import com.huanshankeji.compose.foundation.layout.BoxScope
import com.huanshankeji.compose.ui.CommonModifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.kobweb.compose.ui.modifiers.imitateComposeUiLayout
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import com.huanshankeji.compose.foundation.layout.BoxScope as CommonBoxScope
import com.huanshankeji.compose.ui.Alignment as CommonAlignment
import com.varabyte.kobweb.compose.foundation.layout.BoxScope as PlatformBoxScope
import com.varabyte.kobweb.compose.ui.Alignment as PlatformAlignment

/**
 * Delegates to [Box] without [com.varabyte.kobweb.compose.ui.Modifier.imitateComposeUiLayout].
 */
@Composable
fun KobwebBox(
    modifier: PlatformModifier = PlatformModifier,
    contentAlignment: PlatformAlignment = PlatformAlignment.TopStart,
    ref: ElementRefScope<HTMLElement>? = null,
    content: @Composable PlatformBoxScope.() -> Unit = {}
) {
    AddKobwebComposeStyleSheet()
    Box(modifier, contentAlignment, ref, content)
}

@Composable
fun KobwebBox(
    modifier: CommonModifier = CommonModifier,
    contentAlignment: CommonAlignment,
    content: @Composable CommonBoxScope.() -> Unit = {}
) =
    KobwebBox(modifier.platformModifier, contentAlignment.platformValue) {
        BoxScope.Impl(this).content()
    }

/**
 * Delegates to [Div] without [com.varabyte.kobweb.compose.ui.Modifier.imitateComposeUiLayout].
 */
@Composable
fun DivBox(
    modifier: PlatformModifier = PlatformModifier,
    content: ContentBuilder<HTMLDivElement>? = null
) =
    Div(modifier.toAttrs(), content)
