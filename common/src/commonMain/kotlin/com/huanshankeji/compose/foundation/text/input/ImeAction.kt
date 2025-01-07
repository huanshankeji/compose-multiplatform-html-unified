package com.huanshankeji.compose.foundation.text.input

import androidx.compose.runtime.Stable

// copied and adapted from `androidx.compose.foundation.text.input.ImeAction`
@kotlin.jvm.JvmInline
value class ImeAction internal constructor(@Suppress("unused") private val value: Int) {

    override fun toString(): String {
        return when (this) {
            None -> "None"
            Default -> "Default"
            Go -> "Go"
            Search -> "Search"
            Send -> "Send"
            Previous -> "Previous"
            Next -> "Next"
            Done -> "Done"
            else -> "Invalid"
        }
    }

    companion object {
        @Stable
        val Default: ImeAction = ImeAction(1)

        @Stable
        val None: ImeAction = ImeAction(0)

        @Stable
        val Go: ImeAction = ImeAction(2)

        @Stable
        val Search: ImeAction = ImeAction(3)

        @Stable
        val Send: ImeAction = ImeAction(4)

        @Stable
        val Previous: ImeAction = ImeAction(5)

        @Stable
        val Next: ImeAction = ImeAction(6)

        @Stable
        val Done: ImeAction = ImeAction(7)
    }
}
