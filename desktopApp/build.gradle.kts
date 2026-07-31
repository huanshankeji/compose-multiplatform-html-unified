import com.huanshankeji.cpnProject
import com.huanshankeji.gitversioning.devCommitOrReleaseVersionProvider

plugins {
    id("com.huanshankeji.team.with-group")
    kotlin("jvm")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

version = providers.devCommitOrReleaseVersionProvider(projectBaseVersion, isRelease).get()

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(cpnProject(project, ":demo"))
    implementation(compose.desktop.currentOs)
}

val `package` = "$group.compose.material.demo"

compose {
    desktop {
        application {
            mainClass = "$`package`.MainKt"
        }
    }
}
