import com.huanshankeji.cpnProject
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    `base-conventions`
    kotlin("multiplatform")
    `compose-multiplatform`
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
            }
        }
        // dep(s) kept here in the app module instead of in `shared`, following the AGP 9 KMP template and migration skill
        jsMain {
            dependencies {
                // Entry point uses `renderComposableInBodyWithViewModelStoreOwner` from `:common`, which is not re-exported by `:demo:shared`.
                implementation(cpnProject(project, ":common"))
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
