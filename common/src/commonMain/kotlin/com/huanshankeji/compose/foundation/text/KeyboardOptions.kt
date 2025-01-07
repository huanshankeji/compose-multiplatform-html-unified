package com.huanshankeji.compose.foundation.text

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.foundation.text.input.ImeAction
import com.huanshankeji.compose.foundation.text.input.KeyboardCapitalization
import com.huanshankeji.compose.foundation.text.input.KeyboardType
import com.huanshankeji.compose.foundation.text.input.PlatformImeOptions

// copied and adapted from `androidx.compose.foundation.text.KeyboardOptions`
@Immutable
class KeyboardOptions(
    val capitalization: KeyboardCapitalization? = null,
    val autoCorrect: Boolean? = null,
    val keyboardType: KeyboardType? = null,
    val imeAction: ImeAction? = null,
    val platformImeOptions: PlatformImeOptions? = null
) {
    companion object {
        @Stable
        val Default = KeyboardOptions()
    }

    // `copy`, `equals`, `hashCode`, and `toString`, are removed. I am hoping to generated them with a plugin to reduce duplicate code.
}
