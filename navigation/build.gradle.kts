import com.huanshankeji.cpnProject
import com.huanshankeji.team.ShreckYe
import com.huanshankeji.team.pomForTeamDefaultOpenSource

plugins {
    `lib-conventions`
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                /*
                Use `api`. See:
                https://github.com/JetBrains/compose-multiplatform-core/blob/jb-main/navigation/navigation-compose/build.gradle
                https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/navigation/navigation-compose/build.gradle
                */
                api(compose.runtime)
                api(commonDependencies.jetbrainsAndroidx.navigation.runtime())
                //implementation("org.jetbrains.compose.annotation-internal:annotation:${DependencyVersions.composeMultiplatform}")
                api(cpnProject(project, ":common")) // for `Modifier` and `Alignment`
                //implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0") // This depends on Compose target '[jscanvas]'.
                // https://github.com/JetBrains/compose-multiplatform-core/blob/f6d989a1ae9cd5895b4fba7821946ead389c4848/navigation/navigation-compose/build.gradle#L58
                api(cpnProject(project, ":lifecycle-viewmodel"))
            }
        }
        composeUiMain {
            dependencies {
                api(commonDependencies.jetbrainsAndroidx.navigation.compose())
            }
        }
    }
}

publishing.publications.withType<MavenPublication> {
    pomForTeamDefaultOpenSource(
        project,
        "Unified Compose Multiplatform navigation $FOR_COMPOSE_TARGETS_IN_TITLE",
        "Unified wrappers of Compose Multiplatform navigation for $FOR_COMPOSE_TARGETS_IN_DESCRIPTION"
    ) {
        ShreckYe()
    }
}
