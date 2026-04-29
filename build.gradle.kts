import org.jetbrains.dokka.gradle.tasks.DokkaGeneratePublicationTask

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

plugins {
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.18.1"
    id("org.jetbrains.dokka")
}

val rootProjectName = rootProject.name
val demoProjectName = "$rootProjectName-demo"
val demoAndroidAppProjectName = "$rootProjectName-demo-android-app"
val demoProject = project(demoProjectName)

apiValidation {
    @OptIn(kotlinx.validation.ExperimentalBCVApi::class)
    klib {
        enabled = true
    }

    ignoredProjects += listOf(
        demoProjectName,
        demoAndroidAppProjectName,
        "$rootProjectName-material-icons-core",
        "$rootProjectName-material-icons-extended",
    )
}

dependencies {
    subprojects.filter { it.name != demoProjectName && it.name != demoAndroidAppProjectName && it.buildFile.exists() }.forEach {
        dokka(it)
    }
}

val dokkaGeneratePublicationHtml by tasks.getting(DokkaGeneratePublicationTask::class)
tasks.register<Sync>("generateSite") {
    group = "site"

    val destRootDir = layout.buildDirectory.dir("site")
    into(destRootDir)
    from(dokkaGeneratePublicationHtml) {
        into("api-documentation")
    }
    from(demoProject.tasks.named("sideBySideBrowserDistribution")) {
        into("demo")
    }
    from(layout.projectDirectory.dir("site"))
}
