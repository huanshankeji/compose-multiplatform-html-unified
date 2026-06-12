plugins {
    id("common-conventions")
    id("com.android.library")
    id("com.huanshankeji.kotlin-abi-validation-conventions")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release", "debug")
    }
}

android {
    namespace = group as String

    compileSdk = androidSdkVersion
}
