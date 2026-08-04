import com.huanshankeji.cpnProject

plugins {
    `base-conventions`
    kotlin("jvm")
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(cpnProject(project, ":demo:shared"))
    implementation(compose.desktop.currentOs)
}

compose {
    desktop {
        application {
            mainClass = "${demoPackage()}.MainKt"
        }
    }
}
