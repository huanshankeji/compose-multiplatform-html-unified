import com.huanshankeji.cpnProject
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    `base-conventions`
    kotlin("multiplatform")
}

kotlin {
    jvmToolchain(17)

    val outputFileName = "app.js"

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                this.outputFileName = outputFileName
            }
        }
        binaries.executable()
    }

    js {
        browser {
            commonWebpackConfig {
                cssSupport { enabled.set(true) }
                scssSupport { enabled.set(true) }
                this.outputFileName = outputFileName
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(cpnProject(project, ":demo:shared"))
                implementation(compose.runtime)
            }
        }
        jsMain {
            dependencies {
                // Entry point uses APIs from `:common` that are not re-exported by `:demo:shared`.
                implementation(cpnProject(project, ":common"))
                implementation(compose.html.core)
                implementation(npm("material-symbols", DependencyVersions.materialSymbols))
            }
        }
        wasmJsMain {
            dependencies {
                implementation(compose.ui)
            }
        }
    }
}
