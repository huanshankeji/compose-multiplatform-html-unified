import com.huanshankeji.cpnProject

plugins {
    /*
    Per the Kotlin Multiplatform Wizard project template and Android conventions,
    there is no need to apply the Compose Multiplatform plugin or set Gradle `group` and `version`
    for an Android application module.
    */
    id("com.android.application")
    kotlin("plugin.compose")
}

dependencies {
    implementation(cpnProject(project, ":demo:shared"))
    // dep(s) kept here in the app module instead of in `shared`, following the AGP 9 KMP template and migration skill
    implementation(commonDependencies.androidx.activity.compose())
    implementation(commonDependencies.androidx.compose.ui.module("tooling-preview"))
    debugImplementation(commonDependencies.androidx.compose.ui.module("tooling"))
}

android {
    namespace = DEMO_PACKAGE
    compileSdk = androidSdkVersion

    defaultConfig {
        applicationId = DEMO_PACKAGE
        minSdk = androidMinSdkVersion
        targetSdk = androidSdkVersion
        versionCode = androidVersionCode
        versionName = providers.projectVersion()
    }
}
