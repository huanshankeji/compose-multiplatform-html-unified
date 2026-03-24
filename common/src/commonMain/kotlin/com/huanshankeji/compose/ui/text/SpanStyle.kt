package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.style.TextDecoration

/**
 * Initially supported subset of `androidx.compose.ui.text.SpanStyle`.
 *
 * The following Compose UI parameters are not yet supported:
 * - `fontSynthesis: FontSynthesis?` — requires porting `FontSynthesis`; CSS supports `font-synthesis` but with different semantics
 * - `fontFamily: FontFamily?` — requires porting `FontFamily`; complex to unify across platforms
 * - `fontFeatureSettings: String?` — requires CSS `font-feature-settings` support on JS DOM
 * - `baselineShift: BaselineShift?` — requires porting `BaselineShift`; CSS supports `vertical-align` but with different semantics
 * - `textGeometricTransform: TextGeometricTransform?` — no direct CSS equivalent
 * - `localeList: LocaleList?` — requires porting `LocaleList`; CSS supports `lang` attribute
 * - `background: Color` — can be supported with CSS `background-color`, but may need careful unification
 * - `shadow: Shadow?` — requires porting `Shadow`; CSS supports `text-shadow`
 * - `platformStyle: PlatformSpanStyle?` — platform-specific, not applicable to common API
 * - `drawStyle: DrawStyle?` — requires porting `DrawStyle`; no direct CSS equivalent
 */
@Immutable
expect class SpanStyle {
    constructor(
        color: Color? = null,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        textDecoration: TextDecoration? = null,
        // fontSynthesis: FontSynthesis? = null,
        // fontFamily: FontFamily? = null,
        // fontFeatureSettings: String? = null,
        // baselineShift: BaselineShift? = null,
        // textGeometricTransform: TextGeometricTransform? = null,
        // localeList: LocaleList? = null,
        // background: Color = Color.Unspecified,
        // shadow: Shadow? = null,
        // platformStyle: PlatformSpanStyle? = null,
        // drawStyle: DrawStyle? = null,
    )

    val color: Color?
    val fontSize: TextUnit
    val fontWeight: FontWeight?
    val fontStyle: FontStyle?
    val letterSpacing: TextUnit
    val textDecoration: TextDecoration?
}
