package com.huanshankeji.compose.material.icons

expect class Icon

// Icons other than `Filled` are not supported yet.
object Icons {
    object Filled
    object Outlined
    object Rounded
    object TwoTone
    object Sharp
    object AutoMirrored {
        object Filled
        object Outlined
        object Rounded
        object TwoTone
        object Sharp

        val Default = Filled
    }

    val Default = Filled
}
