import com.huanshankeji.cpnProject
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    `common-conventions`
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    android {
        namespace = "$group.compose.material.demo"

        compileSdk = androidSdkVersion
    }

    // Explicitly connect androidMain to the composeUiMain intermediate source set,
    // as `withAndroidTarget()` in `applyDefaultHierarchyTemplate` doesn't recognize the
    // Android target created by the `com.android.kotlin.multiplatform.library` plugin.
    sourceSets.androidMain {
        dependsOn(sourceSets.getByName("composeUiMain"))
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

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
                implementation(compose.runtime)
                //implementation(cpnProject(project, ":material2"))
                implementation(cpnProject(project, ":material3"))
                implementation(cpnProject(project, ":material-icons:extended"))
                implementation(cpnProject(project, ":navigation"))
                /*
                see https://github.com/JetBrains/compose-multiplatform-core/blob/476d43b99a27696d12ef087e8028d90789645ba7/compose/ui/ui/build.gradle#L54
                and https://github.com/JetBrains/compose-multiplatform-core/blob/381796b5e682653aa1fa53e6bcf0441d06b873f8/compose/runtime/runtime/build.gradle#L124
                 */
                implementation(commonDependencies.kotlinx.coroutines.core())
            }
        }
        composeUiMain {
            dependencies {
                implementation(compose.ui)
            }
        }
        jvmMain {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        androidMain {
            dependencies {
                implementation(commonDependencies.androidx.activity.compose())
                implementation(commonDependencies.androidx.compose.ui.module("tooling-preview"))
            }
        }
        jsMain {
            dependencies {
                implementation(compose.html.core)
                implementation(npm("material-symbols", DependencyVersions.materialSymbols))
            }
        }
    }
}

val `package` = "$group.compose.material.demo"

compose {
    desktop {
        application {
            mainClass = "$`package`.MainKt"
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
