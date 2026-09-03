import com.huanshankeji.cpnProject

plugins {
    `base-conventions`
    kotlin("jvm")
    `compose-multiplatform`
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(cpnProject(project, ":demo:shared"))
    // dep(s) kept here in the app module instead of in `shared`, following the AGP 9 KMP template and migration skill
    implementation(compose.desktop.currentOs)
}

compose {
    desktop {
        application {
            mainClass = "$DEMO_PACKAGE.MainKt"
        }
    }
}
