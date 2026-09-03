// This script is now only applied in `lib-conventions-without-publishing` now. Consider inlining and removing it if necessary.

import com.android.build.api.withAndroid
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("base-conventions")
    id("com.huanshankeji.kotlin-multiplatform-conventional-targets") // kotlin("multiplatform")
    id("compose-multiplatform")
}

kotlin {
    // for Compose UI

    jvm() // TODO: `jvm("desktop")`?
    jvmToolchain(17)

    iosArm64()
    iosSimulatorArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }


    // for JS and HTML wrappers

    js {
        // The project works without this, but it can be added to avoid potential issues.
        browser()
    }



    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("composeUi") {
                withJvm()
                // KT-80409. Android Studio Android view still omits androidMain; Project view and navigation work.
                withAndroid()
                group("ios")
                withWasmJs()
            }
        }
    }



    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
        optIn.add("com.huanshankeji.compose.ExperimentalApi")
        optIn.add("com.huanshankeji.compose.InternalApi")
    }
}
