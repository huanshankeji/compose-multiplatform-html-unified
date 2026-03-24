import com.huanshankeji.cpnProject
import com.huanshankeji.team.ShreckYe
import com.huanshankeji.team.setUpPomForTeamDefaultOpenSource

plugins {
    `lib-conventions`
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                api(cpnProject(project, ":material-icons:core"))
            }
        }
        composeUiMain {
            dependencies {
                api("org.jetbrains.compose.material:material-icons-extended:${DependencyVersions.composeMultiplatformMaterialIconsExtended}")
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
