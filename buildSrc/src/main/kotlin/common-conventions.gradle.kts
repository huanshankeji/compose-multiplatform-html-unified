plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

repositories {
    mavenLocal() // TODO comment out when not needed so the build is always reproducible by others
    mavenCentral()
    google()
}

group = "com.huanshankeji"
version = projectVersion

kotlin {
    // for JS and HTML wrappers

    js {
        // The project works without this, but it can be added to avoid potential issues.
        browser()

        compilerOptions {
            target.set("es2015")
        }
    }



    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
        optIn.add("com.huanshankeji.compose.ExperimentalApi")
    }
}
