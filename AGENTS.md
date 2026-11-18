# Repository Guidelines

## Project Structure

This Kotlin Multiplatform library exposes shared APIs for rendering-based Compose UI and DOM-based Compose HTML. `common/` contains foundation and UI wrappers. `material2/`, `material3/`, `material-icons/{core,extended}/`, `navigation/`, and `lifecycle-viewmodel/` provide additional APIs. `buildSrc/` holds shared Gradle conventions.

Library code lives under `<module>/src/<sourceSet>/kotlin/`. `demo/shared/` contains the sample UI used by `demo/{androidApp,desktopApp,webApp}/`. Browser assets live in `demo/webApp/src/{jsMain,wasmJsMain}/resources/`. The side-by-side demo shell lives in `demo/side-by-side-site/` and the GitHub Pages entry point in `site/`.

## Build and Run

Use the Gradle wrapper with JDK 17, matching CI. Install Android SDK API 37 for Android builds. See [CONTRIBUTING.md](CONTRIBUTING.md) for GitHub Packages credential setup.

- `./gradlew build` builds and verifies the configured projects.
- `./gradlew -p demo/desktopApp run` launches the desktop demo.
- `./gradlew -p demo/webApp jsBrowserDevelopmentRun` starts the Compose HTML demo. Use `wasmJsBrowserDevelopmentRun` for Compose UI in the browser.
- `./gradlew :generateSite` builds the demos and Dokka documentation into `build/site/`.

## Kotlin Style and Naming

Use four-space indentation and match neighboring Kotlin declarations. Use PascalCase for types and UI composables such as `Button`, and lowerCamelCase for ordinary functions and properties. Put shared APIs and `expect` declarations in `commonMain`. Follow the platform suffix convention for corresponding `actual` implementations, such as `Button.composeUi.kt` in `composeUiMain` and `Button.js.kt` in `jsMain`.

## Verification

Run `./gradlew check` before submitting. For UI changes, exercise the affected behavior in both browser demos. When adding shared regression tests, use `kotlin.test` in the owning module’s `src/commonTest/kotlin/` and name files after the behavior under test, such as `ButtonTest.kt`. For intentional public API changes, run `./gradlew apiDump` and review tracked `api/` changes.

## Commits and Pull Requests

Use short imperative subjects such as `Improve link formatting in README.md`. In the PR, describe the behavior change and validation, link the related issue or discussion when available, and include screenshots for visible demo changes.
