import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

repositories {
    mavenLocal() // TODO comment out when not needed so the build is always reproducible by others
    mavenCentral()
    google()
}

group = "com.huanshankeji"
version = projectVersion

kotlin {
    // for Compose UI

    jvm() // TODO: `jvm("desktop")`?
    jvmToolchain(17)

    //androidTarget()

    iosX64()
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



    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}
