import com.huanshankeji.cpnProject
import com.huanshankeji.team.ShreckYe
import com.huanshankeji.team.pomForTeamDefaultOpenSource

plugins {
    `lib-conventions`
}

kotlin {
    sourceSets {
        /*
        Use `api`. See:
        https://github.com/JetBrains/compose-multiplatform-core/blob/jb-main/compose/material/material/build.gradle
        https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/compose/material/material/build.gradle
        */
        commonMain {
            dependencies {
                api(compose.runtime)
                implementation("org.jetbrains.compose.annotation-internal:annotation:${DependencyVersions.composeMultiplatform}")
                api(cpnProject(project, ":common"))
                api(cpnProject(project, ":material-icons-core"))
                //compileOnly(compose.material) // for KDoc element links only
                implementation(commonDependencies.kotlinx.coroutines.core())
            }
        }
        composeUiMain {
            dependencies {
                //implementation(compose.foundation) // not needed
                implementation(compose.material)
            }
        }
        jsMain {
            dependencies {
                // copied from https://github.com/mpetuska/kmdc

                // Be lazy and use the shortcut
                implementation("dev.petuska:kmdc:${DependencyVersions.kmdc}")
                implementation("dev.petuska:kmdcx:${DependencyVersions.kmdc}")
                // TODO: pick and choose exact components to reduce bundle size
            }
        }
    }
}

publishing.publications.withType<MavenPublication> {
    pomForTeamDefaultOpenSource(
        project,
        "Unified Compose Material 2 wrappers $FOR_COMPOSE_TARGETS_IN_TITLE",
        "Unified Material Design 2 component wrappers $FOR_COMPOSE_TARGETS_IN_DESCRIPTION"
    ) {
        ShreckYe()
    }
}
