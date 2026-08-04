import com.huanshankeji.cpnProject

plugins {
    `base-compose-conventions`
    id("com.android.application")
}

dependencies {
    implementation(cpnProject(project, ":demo:shared"))
    // dep(s) kept here in the app module instead of in `shared`, following the AGP 9 KMP template and migration skill
    implementation(commonDependencies.androidx.activity.compose())
    implementation(commonDependencies.androidx.compose.ui.module("tooling-preview"))
    debugImplementation(compose.uiTooling)
}

android {
    namespace = DEMO_PACKAGE
    compileSdk = androidSdkVersion

    defaultConfig {
        applicationId = DEMO_PACKAGE
        minSdk = androidMinSdkVersion
        targetSdk = androidSdkVersion
        versionName = version as String
    }

    buildFeatures {
        compose = true
    }
}
