import com.huanshankeji.team.`Shreck Ye`
import com.huanshankeji.team.pomForTeamDefaultOpenSource

plugins {
    `lib-conventions`
}

kotlin {
    sourceSets {
        /*
        Use `implementation`. See:
        https://github.com/JetBrains/compose-multiplatform-core/blob/jb-main/compose/material/material-icons-core/build.gradle
        https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/compose/material/material-icons-core/build.gradle
        */
        commonMain {
            dependencies {
                implementation(compose.runtime)
            }
        }
        composeUiMain {
            dependencies {
//                api("org.jetbrains.compose.material:material-icons-extended:${DependencyVersions.composeMultiplatform}")
            }
        }
    }
}

publishing.publications.withType<MavenPublication> {
    pomForTeamDefaultOpenSource(
        project,
        "Compose Multiplatform HTML Unified core Icon wrappers",
        "Compose Multiplatform HTML Unified Design core Icon wrappers for `androidx.compose` and Compose HTML"
    ) {
        `Shreck Ye`()
    }
}
