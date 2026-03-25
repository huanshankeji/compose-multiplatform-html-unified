import com.huanshankeji.team.ShreckYe
import com.huanshankeji.team.setUpPomForTeamDefaultOpenSource

plugins {
    `lib-conventions`
}

kotlin {
    sourceSets {
        /*
        Use `implementation`. See:
        https://github.com/JetBrains/compose-multiplatform-core/blob/v1.7.3/compose/material/material-icons-core/build.gradle
        */
        commonMain {
            dependencies {
                implementation(compose.runtime)
            }
        }
        composeUiMain {
            dependencies {
                api("org.jetbrains.compose.material:material-icons-core:${DependencyVersions.composeMultiplatformMaterialIcons}")
            }
        }
    }
}

mavenPublishing {
    pom {
        setUpPomForTeamDefaultOpenSource(
            project,
            "Compose Multiplatform HTML Unified core Icon wrappers",
            "Compose Multiplatform HTML Unified Design core Icon wrappers for `androidx.compose` and Compose HTML",
            "2024"
        ) {
            ShreckYe()
        }
    }
}
