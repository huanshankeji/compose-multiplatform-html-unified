package com.huanshankeji.compose

@RequiresOptIn(
    "This API is more akin to the original `androidx.compose` one but does not follow the conventions and cannot produce the perfect outcome on JS (Compose HTML). " +
            "Consider using the corresponding alternative in the `ext` package instead if you have time to refactor the code.",
    RequiresOptIn.Level.WARNING
)
@Retention(AnnotationRetention.BINARY)
annotation class ExtRecommendedApi