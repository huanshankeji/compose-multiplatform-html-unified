@file:OptIn(com.huanshankeji.GradleCommonExperimentalApi::class)

import com.huanshankeji.artifacts.googleWithContentFiltering
import com.huanshankeji.artifacts.mavenRepositoryHandlerContext
import com.huanshankeji.team.artifacts.mavenCentralExcludingHuanshankeji
import com.huanshankeji.team.gitversioning.opensourcemavenconvention.githubpackages.huanshankejiGithubPackagesOpenSourceMavenConventionProjectRepositories

pluginManagement {
    repositories {
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
                    // https://github.com/huanshankeji/gradle-common/blob/main/kotlin-common/gradle-library/src/main/kotlin/com/huanshankeji/github/packages/maven/GithubPackagesMavenRegistry.kt
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
                includeVersionByRegex("""com\.huanshankeji(\..+)?""", ".*", """.*-dev-commit-[0-9a-f]+.*""")
            }
        }
    }
}

buildscript {
    val gradleCommonPluginsVersion =
        "0.12.0-dev-commit-656d3d5f54d76c571b79f96ecc236cb54b013f50"
    dependencies {
        classpath("com.huanshankeji.team:settings-gradle-plugins:$gradleCommonPluginsVersion")
    }
}

plugins {
    val gradleCommonPluginsVersion =
        "0.12.0-dev-commit-656d3d5f54d76c571b79f96ecc236cb54b013f50"
    id("com.huanshankeji.base-settings-conventions") version gradleCommonPluginsVersion
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentralExcludingHuanshankeji()
        googleWithContentFiltering()
        mavenRepositoryHandlerContext(providers, ::uri) {
            // compose-html-material also publishes compose-html-common / compose-html-material*
            huanshankejiGithubPackagesOpenSourceMavenConventionProjectRepositories(
                "compose-html-material",
                moduleRegex = """compose-html-.*""",
            )
        }
    }
}

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
