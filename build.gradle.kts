tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

plugins {
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.18.1"
    id("org.jetbrains.dokka")
}

val rootProjectName = rootProject.name
val demoProjectName = "$rootProjectName-demo"

apiValidation {
    @OptIn(kotlinx.validation.ExperimentalBCVApi::class)
    klib {
        enabled = true
    }

    ignoredProjects += listOf(demoProjectName)
}

dependencies {
    subprojects.filter { it.name != demoProjectName }.forEach {
        dokka(it)
    }
}
