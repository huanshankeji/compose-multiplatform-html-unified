plugins {
    `lib-conventions-without-publishing`
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                //compileOnly(compose.foundation) // for KDoc element links only
            }
        }
        composeUiMain {
            dependencies {
                api(compose.foundation)
            }
        }
        jsMain {
            dependencies {
                implementation(compose.html.core)

                api("com.huanshankeji:compose-html-common:${DependencyVersions.huanshankejiComposeHtml}")
            }
        }
    }
}

/*
publishing.publications.withType<MavenPublication> {
    pomForTeamDefaultOpenSource(
        project,
        "Legacy Compose Multiplatform common wrappers",
        "Legacy common wrappers of components (including layouts) and styles for Compose Multiplatform on (desktop/Android and web)\n" +
                "This legacy module depends on `com.huanshankeji:compose-html-common` instead of Kobweb Silk " +
                "and its components use the `ModifierOrAttrsScope` class to configure styles."
    ) {
        `Shreck Ye`()
    }
}
*/
