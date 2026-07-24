plugins {
    id("common-conventions")
    id("com.huanshankeji.kotlin-multiplatform-conventional-targets")
    id("com.android.library")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release", "debug")
    }

    /*
    sourceSets {
        val composeUiMain by creating { dependsOn(commonMain.get()) }
        jvmMain { dependsOn(composeUiMain) }
        iosMain { dependsOn(composeUiMain) }
        named("wasmJsMain") { dependsOn(composeUiMain) }
    }
    */
}

android {
    namespace = group as String

    compileSdk = androidSdkVersion
}
