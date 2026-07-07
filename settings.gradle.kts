pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google()
        exclusiveContent {
            forRepository {
                mavenLocal()
            }
            forRepository {
                maven {
                    // Resolves gradle-common plugins when they are not in mavenLocal().
                    // Mirrors gradle-common credential resolution; its APIs cannot be called from settings.gradle.kts:
                    // https://github.com/huanshankeji/gradle-common/blob/main/kotlin-common-gradle-plugins/src/main/kotlin/com/huanshankeji/github/packages/maven/GithubPackagesMavenRegistry.kt
                    url = uri("https://maven.pkg.github.com/huanshankeji/gradle-common")
                    credentials {
                        with(providers) {
                            username = gradleProperty("gpr.user").orElse(gradleProperty("gprUser")).getOrNull()
                            password = gradleProperty("gpr.key").orElse(gradleProperty("gprKey")).getOrNull()
                        }
                    }
                }
            }
            filter {
                includeVersionByRegex("com\\.huanshankeji", ".*", ".*-dev-commit-[0-9a-f]+$")
            }
        }
    }
}

plugins {
    // `base-settings-conventions` is disabled: resolving it pulls gradle-common settings plugins
    // that depend on Gradle 9.6 APIs, which breaks AGP 8.13.x on Gradle 9.6+ until AGP is upgraded.
    // Stay on Gradle 9.5.1 and apply Foojay directly until then.
    // id("com.huanshankeji.base-settings-conventions") version
    //     "0.12.0-dev-commit-99eef7d1f0a66457d59584439a68fd3cf5cac1cb"
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

apply(from = "gradle/dependency-repositories.gradle.kts")

rootProject.name = "compose-multiplatform-html-unified"

include("common")
include("material-icons:core")
include("material-icons:extended")
include("material2")
include("material3")
include("navigation")
include("lifecycle-viewmodel")
include("demo")

fun ProjectDescriptor.setProjectConcatenatedNames(prefix: String) {
    name = prefix + name
    for (child in children)
        child.setProjectConcatenatedNames("$name-")
}
rootProject.setProjectConcatenatedNames("")
