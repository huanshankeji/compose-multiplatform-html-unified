rootProject.name = "compose-multiplatform-html-unified"

include("common") // TODO consider splitting into several modules including `foundation`, `ui`, etc. (#45)
include("material-icons-core")
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

/*
// This seems not needed.
pluginManagement {
    repositories {
        //mavenLocal()
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
*/

// This is needed for Kotlin Native.
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
}
