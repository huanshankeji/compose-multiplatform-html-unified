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
    val kotlinVersion = "2.2.20"
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.9.0")
    implementation("com.huanshankeji.team:gradle-plugins:0.10.0-SNAPSHOT") // TODO don't use a snapshot version in a main branch
    implementation("com.android.tools.build:gradle:8.12.0") // The latest is 8.13.0 but not compatible yet.
    implementation("com.huanshankeji:common-gradle-dependencies:0.10.0-20250918-SNAPSHOT") // TODO don't use a snapshot version in a main branch
}
