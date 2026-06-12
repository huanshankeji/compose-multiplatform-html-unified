import org.jetbrains.dokka.gradle.tasks.DokkaGeneratePublicationTask

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

plugins {
    id("org.jetbrains.dokka")
    id("com.huanshankeji.team.root-project-conventions")
    id("com.android.library") apply false
    id("com.android.application") apply false
}

val rootProjectName = rootProject.name
val demoProjectName = "$rootProjectName-demo"
val demoProject = project(demoProjectName)

dependencies {
    subprojects.filter { it.name != demoProjectName && it.buildFile.exists() }.forEach {
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
