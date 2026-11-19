# Repository Guidelines

Before working in this repository, **load and read** the [Huanshankeji general agent instructions](https://github.com/huanshankeji/.github/blob/main/docs/general-agent-instructions.md) as the required organization-wide baseline. A skipped or failed load, or an unread document, is a blocking error until the document is in context and has been read.

## Project Structure

This Kotlin Multiplatform library exposes shared APIs for rendering-based Compose UI and DOM-based Compose HTML. `common/` contains foundation and UI wrappers. `material2/`, `material3/`, `material-icons/{core,extended}/`, `navigation/`, and `lifecycle-viewmodel/` provide additional APIs. `buildSrc/` holds shared Gradle conventions.

Material 2 is legacy and is excluded from publication. Check the [supported API catalog](README.md#supported-api-catalog) for available wrappers and the status of experimental modules.

Library code lives under `<module>/src/<sourceSet>/kotlin/`. `demo/shared/` contains the sample UI used by `demo/{androidApp,desktopApp,webApp}/`. Browser assets live in `demo/webApp/src/{jsMain,wasmJsMain}/resources/`. The side-by-side demo shell lives in `demo/side-by-side-site/` and the GitHub Pages entry point in `site/`.

## Build and Run

Use the Gradle wrapper with JDK 17, matching CI. Install Android SDK API 37 for Android builds. See [CONTRIBUTING.md](CONTRIBUTING.md) for GitHub Packages credential setup.

For unpublished sibling library changes, follow the [organization’s local development workflow](https://github.com/huanshankeji/.github/blob/main/docs/dev-instructions.md#local-development-workflow).

Review and include intentional updates to the tracked [JS](kotlin-js-store/yarn.lock) and [Wasm](kotlin-js-store/wasm/yarn.lock) Yarn lockfiles with dependency changes.

- `./gradlew build` builds and verifies the configured projects.
- Run `./gradlew checkKotlinAbi` to compare the current ABI with the tracked reference snapshots. For intentional ABI changes, run `./gradlew updateKotlinAbi` and review the generated diff before including the updated snapshots. See [Kotlin’s ABI validation guide](https://kotlinlang.org/docs/gradle-binary-compatibility-validation.html).
- `./gradlew -p demo/desktopApp run` launches the desktop demo.
- `./gradlew -p demo/webApp jsBrowserDevelopmentRun` starts the Compose HTML demo. Use `wasmJsBrowserDevelopmentRun` for Compose UI in the browser.
- `./gradlew :compose-multiplatform-html-unified-demo:sideBySideBrowserDistribution` assembles both browser demos for comparison.
- `./gradlew :generateSite` builds the demos and Dokka documentation into `build/site/`.

## Unified APIs

When adding or aligning a Material 3 component, use the [Material Design 3 component guidance](https://m3.material.io/components/) to find its Jetpack Compose and Material Web references where available.

- Put shared APIs and `expect` declarations in `commonMain`. Delegate to Compose UI in `composeUiMain` and implement the DOM side in `jsMain` using Compose HTML and its wrappers. Check each module's source-set hierarchy before choosing where to put an implementation. Follow platform file suffixes such as `Button.composeUi.kt` and `Button.js.kt`.
- Mirror the upstream Compose UI type shape when choosing an `expect interface`, `expect enum class`, or `expect class`. Use a Compose UI type alias when the platform type satisfies the common declaration. Wrap the platform type when the common API needs extra members or defaults. Implement the JS type locally. Use an ordinary common type when no platform-specific implementation is needed.
- Search both the main and `ext` packages and the existing demos before adding a wrapper. In `com.huanshankeji.compose.material3`, keep mandatory parameters equivalent to Compose UI and match common optional types, allowing equivalent slot defaults such as a nullable slot and an empty lambda. Use `ext` when differences in platform UX or content models require a different API, as with native select versus Compose UI dropdowns.
- Keep platform suffixes such as `JsDom` and `ComposeUi` for platform-specific parameters. Explain platform limits in KDoc and document skipped parameters at the implementation site. List unsupported upstream parameters, constants or members and their reasons in the common API's KDoc. Use `@MaterialWebLabsApi` for JS implementations backed by Material Web Labs. Opt in within a unified wrapper when it can provide consistent behavior on both backends.
- When adapting Compose UI code, start from source matching the project's Compose version. Preserve supported names, types, semantics and declaration order. Add a source reference in a `Copied and adapted` comment. When a DOM implementation deliberately diverges, explain why and record the relevant commit when replacing an earlier design, as in [AnnotatedString](common/src/jsMain/kotlin/com/huanshankeji/compose/ui/text/AnnotatedString.js.kt).

## Kotlin Style and Naming

Use four-space indentation and match neighboring Kotlin declarations. Use PascalCase for types and UI composables such as `Button`, and lowerCamelCase for ordinary functions and properties.

- Use trailing commas in multiline Compose-related declarations and calls with multiple parameters or arguments. Put the comma before any end-of-line comment. Omit it for single-line lists, single-parameter lists, annotations and code outside Compose.
- Write nullable composable types as `@Composable (() -> Unit)?` and parenthesize a composable function type used as an extension receiver, as in `fun (@Composable (Modifier) -> Unit).foo()`. Use `== null` and `!= null` for null checks.
- In DOM code, prefer typed CSS helpers from Compose HTML, Kobweb or `compose-html-common`. Use a raw CSS property when no helper fits and note reusable gaps for upstream. Prefer the [`fillMax*Stretch` variants](common/src/jsMain/kotlin/com/huanshankeji/compose/foundation/layout/ext/Size.js.kt) for full available size in layouts with padding or margins, and use fractional `fillMax*` when needed.

## Verification

Run `./gradlew check` before submitting.

Add each new Material 3 wrapper to the [Material 3 demo](demo/shared/src/commonMain/kotlin/com/huanshankeji/compose/multiplatformhtmlunified/demo/Material3.kt) with a labelled usage example and update the [README catalog](README.md#supported-api-catalog). Exercise affected behavior in the JS DOM and Wasm Compose UI demos. Compare sizing, spacing, slots, colors, shapes and interaction states. Use the URLs printed by Gradle and the browser accessibility tree to inspect canvas controls. Document meaningful backend differences in KDoc or implementation comments.

When adding shared regression tests, use `kotlin.test` in the owning module’s `src/commonTest/kotlin/` and name files after the behavior under test, such as `ButtonTest.kt`.

## Commits and Pull Requests

Use short imperative subjects such as `Improve link formatting in README.md`. In the PR, describe the behavior change and validation, link the related issue or discussion when available, and include screenshots for visible demo changes.
