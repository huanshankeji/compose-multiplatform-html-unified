plugins {
    id("common-conventions")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    android {
        namespace = defaultAndroidNamespace()
        compileSdk = androidSdkVersion
        minSdk = androidMinSdkVersion
    }
}
