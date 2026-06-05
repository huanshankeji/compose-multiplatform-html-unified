plugins {
    id("common-conventions")
    id("com.huanshankeji.kotlin-multiplatform-conventional-targets")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    android {
        // Derive unique namespace from project path to satisfy AGP 9's unique namespace requirement
        namespace = "$group.${project.path.removePrefix(":").replace(":", ".").replace("-", ".")}"

        compileSdk = androidSdkVersion
    }

    // Explicitly connect androidMain to the composeUiMain intermediate source set,
    // as `withAndroidTarget()` in `applyDefaultHierarchyTemplate` doesn't recognize the
    // Android target created by the `com.android.kotlin.multiplatform.library` plugin.
    sourceSets.androidMain {
        dependsOn(sourceSets.getByName("composeUiMain"))
    }
}
