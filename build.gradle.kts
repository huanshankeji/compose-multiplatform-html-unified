import org.jetbrains.dokka.gradle.tasks.DokkaGeneratePublicationTask

plugins {
    id("com.huanshankeji.root-project-conventions")
    id("org.jetbrains.dokka")
}

val rootProjectName = rootProject.name
val nonLibraryProjectNameSuffixes = setOf("demo", "demo-shared", "demo-androidApp", "demo-desktopApp", "demo-webApp")
val nonLibraryProjectNames = nonLibraryProjectNameSuffixes.map { "$rootProjectName-$it" }.toSet()
val webAppProject = project(":$rootProjectName-demo:$rootProjectName-demo-webApp")

dependencies {
    subprojects.filter { it.name !in nonLibraryProjectNames && it.buildFile.exists() }.forEach {
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
    from(webAppProject.tasks.named("sideBySideBrowserDistribution")) {
        into("demo")
    }
    from(layout.projectDirectory.dir("site"))
}
