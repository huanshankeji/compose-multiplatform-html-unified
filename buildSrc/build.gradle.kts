plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    gradlePluginPortal()
    google()
}

val gradleCommonPluginsVersion =
    "0.12.0-dev-commit-55b05c18844ec9f2326792a8f473a53d9d142d68"

dependencies {
    implementation(kotlin("gradle-plugin", "2.4.0"))
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.4.0")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.10.3")
    implementation("com.huanshankeji.team:gradle-plugins:$gradleCommonPluginsVersion")
    implementation("com.huanshankeji:kotlin-common-gradle-plugins:$gradleCommonPluginsVersion")
    implementation("com.android.tools.build:gradle:8.13.2")
    implementation("com.huanshankeji:common-gradle-dependencies:0.10.0-20251024")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:2.2.0")
}
