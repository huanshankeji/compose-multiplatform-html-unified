plugins {
    id("multiplatform-conventions")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    android {
        compileSdk = androidSdkVersion
        minSdk = androidMinSdkVersion
        // Preserve the published Android module's compiler-generated ABI names.
        compilerOptions.moduleName.set("${project.group}:${project.name}_release")
    }
}
