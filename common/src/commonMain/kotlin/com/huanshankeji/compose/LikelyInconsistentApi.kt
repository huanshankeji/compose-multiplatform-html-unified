package com.huanshankeji.compose

@RequiresOptIn(
    "This API is likely to be inconsistent now on Compose UI and Compose HTML, which could affect the final visual effect. " +
            "Please verify on both kinds of targets to make sure it works as expected. " +
            "You are welcome to contribute to this library if you have a solution to fix the consistency.",
    RequiresOptIn.Level.WARNING
)
@Retention(AnnotationRetention.BINARY)
annotation class LikelyInconsistentApi