plugins {
    id("multiplatform-conventions")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    android {
        compileSdk = androidSdkVersion
        minSdk = androidMinSdkVersion
    }
}
