import gradle.kotlin.dsl.accessors._735e0b256074a56bbc8b6a8e54c615e3.kotlin

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
