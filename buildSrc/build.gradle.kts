plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal() // TODO comment out when not needed so the build is always reproducible by others
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    val kotlinVersion = "2.2.21"
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.9.1")
    implementation("com.huanshankeji.team:gradle-plugins:0.10.0") // don't use a snapshot version in a main branch
    // https://developer.android.com/build/releases/gradle-plugin, https://mvnrepository.com/artifact/com.android.tools.build/gradle
    implementation("com.android.tools.build:gradle:8.12.3")
    implementation("com.huanshankeji:common-gradle-dependencies:0.10.0-20251024") // don't use a snapshot version in a main branch
}
