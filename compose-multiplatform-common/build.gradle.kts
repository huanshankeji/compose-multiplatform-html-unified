import com.huanshankeji.team.`Shreck Ye`
import com.huanshankeji.team.pomForTeamDefaultOpenSource

plugins {
    `lib-conventions`
}

kotlin {
    sourceSets {
        /*
        Use `api`. See:
        https://github.com/JetBrains/compose-multiplatform-core/blob/jb-main/compose/foundation/foundation/build.gradle
        https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-main/compose/foundation/foundation/build.gradle
        */
        commonMain {
            dependencies {
                api(compose.runtime)
                //compileOnly(compose.foundation) // for KDoc element links only
                /*
                The units from Compose are used directly.
                This is an ideal dependency whose APIs should be exposed and shared on all platforms including JS
                therefore we don't need to create wrappers for them,
                because a depending `ui-graphics` module would be too much as it depends on `skiko`,
                and more such as `ui` and `ui-text` depend on `ui-graphics`.
                A dependency of `ui-unit` is `ui-geometry` which might be useful too.
                */
                api("org.jetbrains.compose.ui:ui-unit:${DependencyVersions.composeMultiplatform}")
                implementation("org.jetbrains.compose.annotation-internal:annotation:${DependencyVersions.composeMultiplatform}")
            }
        }
        androidxCommonMain {
            dependencies {
                api(compose.foundation)
                implementation(compose.ui)
            }
        }
        jsMain {
            dependencies {
                api(compose.html.core)
                // see: https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/build.gradle.kts
                api("com.varabyte.kobweb:kobweb-compose:${DependencyVersions.kobweb}")
                implementation("com.huanshankeji:compose-html-common:${DependencyVersions.huanshankejiComposeHtml}")
            }
        }
    }
}

publishing.publications.withType<MavenPublication> {
    pomForTeamDefaultOpenSource(
        project,
        "Unified Compose Multiplatform common wrappers $FOR_COMPOSE_TARGETS_IN_TITLE",
        "Common wrappers of components (including layouts) and modifiers $FOR_COMPOSE_TARGETS_IN_DESCRIPTION"
    ) {
        `Shreck Ye`()
    }
}
