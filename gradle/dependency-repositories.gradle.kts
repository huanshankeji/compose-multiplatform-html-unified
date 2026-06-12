@Suppress("UnstableApiUsage")

// Local dependency repository wiring (Android KMP). The settings plugin's
// dependencyResolutionManagement hook is not used here because it breaks AGP version inference.

val huanshankejiGroup = "com.huanshankeji"
val devCommitVersionRegex = ".*-dev-commit-[0-9a-f]+$"
val dirtyDevCommitVersionRegex = ".*-dev-commit-[0-9a-f]+-dirty-SNAPSHOT$"
val legacySnapshotVersionRegex = ".*-SNAPSHOT$"

fun org.gradle.api.artifacts.repositories.MavenArtifactRepository.githubPackagesMaven(repositoryName: String) {
    name = "GitHubPackages-$repositoryName"
    url = uri("https://maven.pkg.github.com/huanshankeji/$repositoryName")
    credentials {
        username = providers.gradleProperty("gpr.user").orNull
            ?: System.getenv("USERNAME")
            ?: System.getenv("GH_ACTOR")
        password = providers.gradleProperty("gpr.key").orNull
            ?: System.getenv("TOKEN")
            ?: System.getenv("GH_TOKEN")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenLocal {
            content {
                includeVersionByRegex(huanshankejiGroup, ".*", devCommitVersionRegex)
                includeVersionByRegex(huanshankejiGroup, ".*", dirtyDevCommitVersionRegex)
                includeVersionByRegex(huanshankejiGroup, ".*", legacySnapshotVersionRegex)
            }
        }
        maven {
            githubPackagesMaven("compose-multiplatform-html-unified")
            content {
                includeVersionByRegex(huanshankejiGroup, ".*", devCommitVersionRegex)
            }
        }
        maven {
            githubPackagesMaven("compose-html-material")
            content {
                includeVersionByRegex(huanshankejiGroup, ".*", devCommitVersionRegex)
            }
        }
        maven {
            githubPackagesMaven("kotlin-common")
            content {
                includeVersionByRegex(huanshankejiGroup, ".*", devCommitVersionRegex)
            }
        }
        maven {
            githubPackagesMaven("gradle-common")
            content {
                includeVersionByRegex(huanshankejiGroup, ".*", devCommitVersionRegex)
            }
        }
        mavenCentral {
            content {
                excludeVersionByRegex(huanshankejiGroup, ".*", devCommitVersionRegex)
                excludeVersionByRegex(huanshankejiGroup, ".*", dirtyDevCommitVersionRegex)
                excludeVersionByRegex(huanshankejiGroup, ".*", legacySnapshotVersionRegex)
            }
        }
    }
}
