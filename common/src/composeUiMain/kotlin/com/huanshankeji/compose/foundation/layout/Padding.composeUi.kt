package com.huanshankeji.compose.foundation.layout

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.foundation.layout.PaddingValues as PlatformPaddingValues

@Stable
actual fun Modifier.padding(start: Dp, top: Dp, end: Dp, bottom: Dp): Modifier =
    platformModify { padding(start, top, end, bottom) }

@Stable
actual fun Modifier.padding(horizontal: Dp, vertical: Dp): Modifier =
    platformModify { padding(horizontal, vertical) }

@Stable
actual fun Modifier.padding(all: Dp): Modifier =
    platformModify { padding(all) }

@Stable
actual fun Modifier.padding(paddingValues: PaddingValues): Modifier =
    platformModify { padding(paddingValues.platformValue) }

@Stable
actual fun Modifier.absolutePadding(left: Dp, top: Dp, right: Dp, bottom: Dp): Modifier =
    platformModify { absolutePadding(left, top, right, bottom) }

//actual typealias PaddingValues = androidx.compose.foundation.layout.PaddingValues
@Stable
actual abstract class PaddingValues(val platformValue: PlatformPaddingValues) {
    @Immutable
    actual class Absolute(platformValue: PlatformPaddingValues.Absolute) : PaddingValues(platformValue) {
        actual constructor(
            left: Dp,
            top: Dp,
            right: Dp,
            bottom: Dp
        ) : this(PlatformPaddingValues.Absolute(left, top, right, bottom))
    }
}

@Stable
actual fun PaddingValues(all: Dp): PaddingValues =
    PaddingValuesImpl(PlatformPaddingValues(all))

@Stable
actual fun PaddingValues(horizontal: Dp, vertical: Dp): PaddingValues =
    PaddingValuesImpl(PlatformPaddingValues(horizontal, vertical))

@Stable
actual fun PaddingValues(start: Dp, top: Dp, end: Dp, bottom: Dp): PaddingValues =
    PaddingValuesImpl(PlatformPaddingValues(start, top, end, bottom))

@Immutable
internal class PaddingValuesImpl(
    platformValue: PlatformPaddingValues
) : PaddingValues(platformValue)

fun PlatformPaddingValues.toCommonValue(): PaddingValues =
    /*
    // This seems not necessary.
    when (this) {
        is PlatformPaddingValues.Absolute -> PaddingValues.Absolute(this)
        else -> PaddingValuesImpl(this)
    }
    */
    PaddingValuesImpl(this)
