import org.gradle.api.Project

val androidSdkVersion = 36
val androidMinSdkVersion = 24

/**
 * Unique Android namespace per module (required by AGP 9's `android.uniquePackageNames` default).
 * Derived from the concatenated project name suffix after the root project name.
 */
fun Project.defaultAndroidNamespace(): String {
    val suffix = name.removePrefix("${rootProject.name}-")
    return "$group.${suffix.replace('-', '.')}"
}
