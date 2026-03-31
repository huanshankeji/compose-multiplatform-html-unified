import com.huanshankeji.cpnProject
import com.huanshankeji.team.ShreckYe
import com.huanshankeji.team.setUpPomForTeamDefaultOpenSource

plugins {
    `lib-conventions`
}

kotlin {
    sourceSets {
        /*
        Use `api`. See:
        https://github.com/JetBrains/compose-multiplatform-core/blob/v1.7.3/compose/material/material-icons-extended/build.gradle
        */
        commonMain {
            dependencies {
                implementation(compose.runtime)
                api(cpnProject(project, ":material-icons:core"))
            }
        }
        composeUiMain {
            dependencies {
                api("org.jetbrains.compose.material:material-icons-extended:${DependencyVersions.composeMultiplatformMaterialIcons}")
            }
        }
    }
}

mavenPublishing {
    pom {
        setUpPomForTeamDefaultOpenSource(
            project,
            "Compose Multiplatform HTML Unified extended Icon wrappers",
            "Compose Multiplatform HTML Unified Design extended Icon wrappers for `androidx.compose` and Compose HTML",
            "2026"
        ) {
            ShreckYe()
        }
    }
}
