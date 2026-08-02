import com.huanshankeji.cpnProject
import com.huanshankeji.gitversioning.devCommitOrReleaseVersionProvider
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("com.huanshankeji.team.with-group")
    kotlin("multiplatform")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

version = providers.devCommitOrReleaseVersionProvider(projectBaseVersion, isRelease).get()

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

val jsBrowserDistribution by tasks.getting(Sync::class)
val wasmJsBrowserDistribution by tasks.getting(Sync::class)

tasks.register<Sync>("sideBySideBrowserDistribution") {
    group = "kotlin browser"

    into(layout.buildDirectory.dir("dist/sideBySide/productionExecutable"))
    from(jsBrowserDistribution) {
        into("js-dom")
    }
    from(wasmJsBrowserDistribution) {
        into("wasm-js-canvas")
    }
    from(projectDir.resolve("side-by-side-site"))
}
