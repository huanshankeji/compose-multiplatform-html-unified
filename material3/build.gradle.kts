import com.huanshankeji.cpnProject
import com.huanshankeji.team.ShreckYe
import com.huanshankeji.team.pomForTeamDefaultOpenSource

plugins {
    `lib-conventions`
    //id("com.android.library")
}

kotlin {
    /*
    androidTarget {
        publishLibraryVariants("release", "debug")
    }
    */

    sourceSets {
        /*
        Use `api`. See:
        https://github.com/JetBrains/compose-multiplatform-core/blob/jb-main/compose/material3/material3/build.gradle
        https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/compose/material3/material3/build.gradle
        */
        commonMain {
            dependencies {
                api(compose.runtime)
                api(cpnProject(project, ":common"))
                api(cpnProject(project, ":material-icons-core"))
                //compileOnly(compose.material) // for KDoc element links only
            }
        }
        composeUiMain {
            dependencies {
                api(compose.material3)
            }
        }
        jsMain {
            dependencies {
                api("com.huanshankeji:compose-html-material3:${DependencyVersions.huanshankejiComposeHtml}") { exclude("org.jetbrains.kotlin") }
                implementation("com.huanshankeji:compose-html-common:${DependencyVersions.huanshankejiComposeHtml}") {
                    exclude("org.jetbrains.kotlin")
                }
            }
        }
    }
}

publishing.publications.withType<MavenPublication> {
    pomForTeamDefaultOpenSource(
        project,
        "Unified Compose Material 3 wrappers $FOR_COMPOSE_TARGETS_IN_TITLE",
        "Unified Material Design 3 component wrappers $FOR_COMPOSE_TARGETS_IN_DESCRIPTION",
        "2024"
    ) {
        ShreckYe()
    }
}
