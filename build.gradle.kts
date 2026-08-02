import com.huanshankeji.getConcatenatedProjectNamePath
import org.jetbrains.dokka.gradle.tasks.DokkaGeneratePublicationTask

plugins {
    id("com.huanshankeji.root-project-conventions")
    id("org.jetbrains.dokka")
}

val demoProject = project(getConcatenatedProjectNamePath(":demo"))
val demoWebAppProject = project(getConcatenatedProjectNamePath(":demo:webApp"))

dependencies {
    subprojects.filter { it !in demoProject.allprojects && it.buildFile.exists() }.forEach {
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
    from(demoWebAppProject.tasks.named("sideBySideBrowserDistribution")) {
        into("demo")
    }
    from(layout.projectDirectory.dir("site"))
}
