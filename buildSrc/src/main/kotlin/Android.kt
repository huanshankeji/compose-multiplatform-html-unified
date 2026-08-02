import org.gradle.api.Project

val androidSdkVersion = 36
val androidMinSdkVersion = 24

/**
 * Unique Android namespace per module (required by AGP 9's `android.uniquePackageNames` default).
 * `$group` plus the full project `name` with dashes removed (valid Java package segment).
 */
fun Project.defaultAndroidNamespace(): String =
    "$group.${name.replace("-", "")}"
