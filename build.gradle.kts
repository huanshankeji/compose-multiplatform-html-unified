tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

plugins {
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.16.3"
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

plugins {
    id("org.jetbrains.dokka")
}

dependencies {
    subprojects.filter { it.name.startsWith(project.name) }.forEach {
        dokka(it)
    }
}
