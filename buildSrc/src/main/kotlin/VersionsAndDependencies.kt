import com.huanshankeji.CommonDependencies
import org.jetbrains.compose.ComposeBuildConfig

val projectVersion = "0.6.0-SNAPSHOT"

val commonDependencies = CommonDependencies()

object DependencyVersions {
    const val composeMultiplatform = ComposeBuildConfig.composeVersion // for "ui-unit"

    // https://slack-chats.kotlinlang.org/t/27579160/after-updating-to-1-8-0-rc01-package-androidx-compose-materi
    // https://github.com/JetBrains/compose-multiplatform/releases/tag/v1.8.0-rc01
    const val composeMultiplatformMaterialIconsExtended = "1.7.3"
    val kobweb = "0.19.2"
    val huanshankejiComposeHtml = "0.4.0" // don't use a snapshot version in a main branch
    val kmdc = "0.1.2"
    val materialSymbols = "0.25.1"
}
