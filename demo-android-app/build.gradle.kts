import com.huanshankeji.cpnProject

plugins {
    id("com.android.application")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

group = "com.huanshankeji"
version = projectVersion

val `package` = "$group.compose.material.demo"

android {
    namespace = "$`package`.app"

    val sdk = androidSdkVersion
    compileSdk = sdk

    defaultConfig {
        applicationId = `package`
        minSdk = 24
        targetSdk = sdk
        versionName = version as String
    }

    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

dependencies {
    implementation(cpnProject(project, ":demo"))
    implementation(commonDependencies.androidx.activity.compose())
    implementation(commonDependencies.androidx.compose.ui.module("tooling-preview"))
}
