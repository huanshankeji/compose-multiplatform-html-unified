import com.huanshankeji.cpnProject

plugins {
    `lib-conventions-without-publishing`
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
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
        jsMain {
            dependencies {
                implementation(compose.html.core)
            }
        }
    }

    android {
        // Sources use `DEMO_PACKAGE` like `:demo:androidApp`; `.shared` keeps AGP namespaces unique.
        namespace = "$DEMO_PACKAGE.shared"
    }
}
