tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

plugins {
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.16.3"
    id("org.jetbrains.dokka")
}

apiValidation {
    @OptIn(kotlinx.validation.ExperimentalBCVApi::class)
    klib {
        enabled = true
    }

    ignoredProjects += "compose-multiplatform-html-unified".let {
        listOf("$it-demo", "$it-common-legacy")
    }
}

dependencies {
    subprojects.filter { it.name.startsWith(project.name) }.forEach {
        dokka(it)
    }
}
