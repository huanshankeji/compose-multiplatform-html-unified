import com.huanshankeji.cpnProject
import com.huanshankeji.gitversioning.devCommitOrReleaseVersionProvider

plugins {
    id("com.huanshankeji.team.with-group")
    id("com.android.application")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

version = providers.devCommitOrReleaseVersionProvider(projectBaseVersion, isRelease).get()

val `package` = "$group.compose.material.demo"

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
