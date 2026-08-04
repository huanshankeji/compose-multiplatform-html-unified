import com.huanshankeji.gitversioning.devCommitOrReleaseVersionProvider

plugins {
    id("com.huanshankeji.team.with-group")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
}

version = providers.devCommitOrReleaseVersionProvider(projectBaseVersion, isRelease).get()
