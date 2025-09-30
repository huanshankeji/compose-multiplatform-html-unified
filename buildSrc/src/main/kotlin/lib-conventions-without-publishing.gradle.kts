plugins {
    id("common-conventions")
    id("com.huanshankeji.kotlin-multiplatform-conventional-targets")
    // id("com.android.library") // Temporarily commented out due to network restrictions
}

kotlin {
    // androidTarget { // Temporarily commented out due to network restrictions
    //     publishLibraryVariants("release", "debug")
    // }

    /*
    sourceSets {
        val composeUiMain by creating { dependsOn(commonMain.get()) }
        jvmMain { dependsOn(composeUiMain) }
        iosMain { dependsOn(composeUiMain) }
        named("wasmJsMain") { dependsOn(composeUiMain) }
    }
    */
}

// android { // Temporarily commented out due to network restrictions
//     namespace = group as String
// 
//     compileSdk = androidSdkVersion
// }
