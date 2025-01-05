import com.huanshankeji.CommonDependencies
import org.jetbrains.compose.ComposeBuildConfig

val projectVersion = "0.5.1-save-viewmodel-in-navigation-on-js-dom-SNAPSHOT"

val commonDependencies = CommonDependencies()

object DependencyVersions {
    const val composeMultiplatform = ComposeBuildConfig.composeVersion // for "ui-unit"
    val kobweb = "0.19.2"
    val huanshankejiComposeHtml = "0.4.0" // don't use a snapshot version in a main branch
    val kmdc = "0.1.2"
    val materialSymbols = "0.25.1"
}
