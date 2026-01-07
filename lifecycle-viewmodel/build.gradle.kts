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
                https://github.com/JetBrains/compose-multiplatform-core/blob/jb-main/lifecycle/lifecycle-viewmodel-compose/build.gradle
                https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/lifecycle/lifecycle-viewmodel-compose/build.gradle
                */
                api(compose.runtime)
                api(commonDependencies.jetbrainsAndroidx.lifecycle.viewmodel())
                // only needed on JS DOM actually
                // https://github.com/JetBrains/compose-multiplatform-core/blob/f1e03d0784631a88201931a6a6a708cdd090be57/lifecycle/lifecycle-viewmodel-compose/build.gradle#L58
                api(cpnProject(project, ":common"))
            }
        }
        composeUiMain {
            dependencies {
                api(commonDependencies.jetbrainsAndroidx.lifecycle.viewmodelCompose())
            }
        }
    }
}

publishing.publications.withType<MavenPublication> {
    pomForTeamDefaultOpenSource(
        project,
        "Unified Compose Multiplatform ViewModel $FOR_COMPOSE_TARGETS_IN_TITLE",
        "Unified wrappers of Compose Multiplatform ViewModel for $FOR_COMPOSE_TARGETS_IN_DESCRIPTION",
        "2024"
    ) {
        ShreckYe()
    }
}
