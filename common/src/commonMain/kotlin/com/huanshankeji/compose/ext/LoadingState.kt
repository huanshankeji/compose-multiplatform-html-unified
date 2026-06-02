package com.huanshankeji.compose.ext

import androidx.compose.runtime.Immutable
import com.huanshankeji.compose.ExperimentalApi

@ExperimentalApi
@Immutable
sealed class LoadingState<out T, out E> {
    object Loading : LoadingState<Nothing, Nothing>()
    data class Loaded<out T>(val value: T) : LoadingState<T, Nothing>()
    data class Error<out E>(val error: E) : LoadingState<Nothing, E>()
}

typealias ThrowableErrorLoadingState<T> = LoadingState<T, Throwable>
typealias StringErrorLoadingState<T> = LoadingState<T, String>
