package com.huanshankeji.compose.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.huanshankeji.compose.foundation.ext.css.horizontalScroll
import com.huanshankeji.compose.foundation.ext.css.verticalScroll
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.kobweb.compose.ui.modifiers.imitateComposeUiLayout
import com.varabyte.kobweb.compose.ui.styleModifier

/**
 * @see com.huanshankeji.compose.foundation.ext.css.verticalScroll
 */
fun PlatformModifier.verticalScroll() =
    styleModifier { verticalScroll() }

/**
 * @see com.huanshankeji.compose.foundation.ext.css.horizontalScroll
 */
fun PlatformModifier.horizontalScroll() =
    styleModifier { horizontalScroll() }

val imitateComposeUiLayoutVerticalScrollPlatformModifier = PlatformModifier.imitateComposeUiLayout().verticalScroll()
val imitateComposeUiLayoutHorizontalScrollPlatformModifier =
    PlatformModifier.imitateComposeUiLayout().horizontalScroll()


@Composable
actual fun rememberScrollState(initial: Int): ScrollState =
    remember { ScrollState() }


// used to be `actual object`, but but Dokka fails with it
@Stable
actual class ScrollState

actual fun Modifier.verticalScroll(state: ScrollState): Modifier =
    platformModify { verticalScroll() }

actual fun Modifier.horizontalScroll(state: ScrollState): Modifier =
    platformModify { horizontalScroll() }
