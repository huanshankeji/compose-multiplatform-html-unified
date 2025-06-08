plugins {
    `kotlin-dsl`
}

repositories {
    //mavenLocal() // comment out when not needed
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    val kotlinVersion = "2.2.0-RC2"
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.8.1")
    implementation("com.huanshankeji.team:gradle-plugins:0.9.0") // don't use a snapshot version in a main branch
    implementation("com.android.tools.build:gradle:8.10.1")
    implementation("com.huanshankeji:common-gradle-dependencies:0.9.0-20241203") // don't use a snapshot version in a main branch
}
