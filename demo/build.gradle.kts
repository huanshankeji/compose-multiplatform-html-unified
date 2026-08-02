import com.huanshankeji.getConcatenatedProjectNamePath

val webAppProject = project(getConcatenatedProjectNamePath(":demo:webApp"))

tasks.register<Sync>("sideBySideBrowserDistribution") {
    group = "kotlin browser"

    into(layout.buildDirectory.dir("dist/sideBySide/productionExecutable"))
    from(webAppProject.tasks.named("jsBrowserDistribution")) {
        into("js-dom")
    }
    from(webAppProject.tasks.named("wasmJsBrowserDistribution")) {
        into("wasm-js-canvas")
    }
    from(projectDir.resolve("side-by-side-site"))
}
