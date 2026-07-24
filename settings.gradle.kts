@file:OptIn(com.huanshankeji.GradleCommonExperimentalApi::class)

import com.huanshankeji.setProjectConcatenatedNames
import com.huanshankeji.artifacts.googleWithContentFiltering
import com.huanshankeji.artifacts.mavenRepositoryHandlerContext
import com.huanshankeji.team.artifacts.mavenCentralExcludingHuanshankeji
import com.huanshankeji.team.gitversioning.opensourcemavenconvention.githubpackages.huanshankejiGithubPackagesOpenSourceMavenConventionProjectRepositories

pluginManagement {
    // Must apply inside this block: Kotlin DSL runs pluginManagement before top-level statements.
    apply(from = "gradle/classpath-bootstrap.gradle.kts")
    @Suppress("UNCHECKED_CAST")
    (extra["repositories"] as RepositoryHandler.() -> Unit)(repositories)
}

buildscript {
    dependencies {
        classpath("com.huanshankeji.team:settings-gradle-plugins:${settings.extra["gradleCommonPluginsVersion"]}")
    }
}

plugins {
    id("com.huanshankeji.base-settings-conventions") version (extra["gradleCommonPluginsVersion"] as String)
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

setProjectConcatenatedNames()
