plugins {
    id("common-conventions")
    id("com.huanshankeji.kotlin-multiplatform-conventional-targets")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    android {
        namespace = defaultAndroidNamespace()
        compileSdk = androidSdkVersion
        minSdk = androidMinSdkVersion
    }
}
