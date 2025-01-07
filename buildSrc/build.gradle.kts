plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    // With Kotlin 2.0.20, a "Could not parse POM" build error occurs in the JVM projects of some dependent projects.
    val kotlinVersion = "2.0.10"
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.7.0")
    implementation("com.huanshankeji.team:gradle-plugins:0.7.0-SNAPSHOT") // TODO don't use a snapshot version in a main branch
    implementation("com.android.tools.build:gradle:8.5.2")
    implementation("com.huanshankeji:common-gradle-dependencies:0.8.0-20241016") // don't use a snapshot version in a main branch
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:2.0.0")
}
