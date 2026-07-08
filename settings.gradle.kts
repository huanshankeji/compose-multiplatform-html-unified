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
    id("com.huanshankeji.base-settings-conventions") version
        "0.12.0-dev-commit-55b05c18844ec9f2326792a8f473a53d9d142d68"
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
