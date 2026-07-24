plugins {
    id("lib-conventions-without-publishing")
    id("com.huanshankeji.team.gitversioning.opensourceconvention.githubpackages.publish")
    id("dokka-convention")
}

gitVersioningOpenSourceConventionGithubPackagesPublish {
    signAllPublicationsIfRelease(isRelease)
}
