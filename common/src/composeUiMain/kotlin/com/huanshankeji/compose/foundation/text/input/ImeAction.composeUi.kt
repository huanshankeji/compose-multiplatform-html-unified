package com.huanshankeji.compose.foundation.text.input

import androidx.compose.ui.text.input.ImeAction as PlatformImeAction

fun ImeAction.toPlatformValue(): PlatformImeAction =
    when (this) {
        ImeAction.Default -> PlatformImeAction.Default
        ImeAction.None -> PlatformImeAction.None
        ImeAction.Go -> PlatformImeAction.Go
        ImeAction.Search -> PlatformImeAction.Search
        ImeAction.Send -> PlatformImeAction.Search
        ImeAction.Previous -> PlatformImeAction.Previous
        ImeAction.Next -> PlatformImeAction.Next
        ImeAction.Done -> PlatformImeAction.Done

        else -> throw IllegalArgumentException(toString())
    }

fun ImeAction?.toPlatformValue(): PlatformImeAction =
    this?.toPlatformValue() ?: PlatformImeAction.Default
