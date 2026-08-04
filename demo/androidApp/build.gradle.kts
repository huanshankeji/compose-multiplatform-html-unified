import com.huanshankeji.cpnProject

plugins {
    `base-conventions`
    id("com.android.application")
}

val `package` = demoPackage()

android {
    namespace = `package`
    compileSdk = androidSdkVersion

    defaultConfig {
        applicationId = `package`
        minSdk = androidMinSdkVersion
        targetSdk = androidSdkVersion
        versionName = version as String
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(cpnProject(project, ":demo:shared"))
    implementation(commonDependencies.androidx.activity.compose())
    implementation(commonDependencies.androidx.compose.ui.module("tooling-preview"))
    debugImplementation(compose.uiTooling)
}
