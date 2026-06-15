import com.huanshankeji.CommonDependencies
import org.jetbrains.compose.ComposeBuildConfig

val projectBaseVersion = "0.6.0"

val gradleCommonPluginsVersion =
    "0.12.0-dev-commit-7fe538f8906aa9460a73cd32390005180fab633e"

val commonDependencies = CommonDependencies()

object DependencyVersions {
    const val composeMultiplatform = ComposeBuildConfig.composeVersion // for "ui-unit"


    // https://slack-chats.kotlinlang.org/t/27579160/after-updating-to-1-8-0-rc01-package-androidx-compose-materi
    // https://github.com/JetBrains/compose-multiplatform/releases/tag/v1.8.0-rc01
    const val composeMultiplatformMaterialIcons = "1.7.3"

    // https://github.com/varabyte/kobweb/releases
    val kobweb = "0.24.0"
    val huanshankejiComposeHtml = "0.5.0"

    // https://github.com/mpetuska/kmdc/releases
    val kmdc = "0.1.2"

    // https://github.com/marella/material-symbols/releases
    val materialSymbols = "0.43.0"
}
