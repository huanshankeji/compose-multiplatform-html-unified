# AGENTS.md

## Overview

**Compose Multiplatform HTML Unified** is a Kotlin Multiplatform library providing unified Compose Multiplatform wrappers of common and Material Design APIs for both **rendering-based Compose UI** (Android, desktop JVM, iOS, web Kotlin/Wasm) and **DOM-based Compose HTML**.

## Build and Test

### Prerequisites

- **JDK 17** (as specified in CI workflows)
- Internet access for initial dependency resolution

### Commands

```bash
./gradlew publishToMavenLocal  # Primary build validation — always run first
./gradlew check                # Run tests and API compatibility checks
```

If `check` fails solely due to `apiCheck` failures (public API changes), do **not** run `apiDump` automatically — leave it for the human developer. Validate with `publishToMavenLocal` instead.

### Demo Validation

```bash
./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun   # JS DOM at localhost:8080
./gradlew :compose-multiplatform-html-unified-demo:wasmJsBrowserDevelopmentRun  # Wasm/Compose UI at localhost:8080
./gradlew :compose-multiplatform-html-unified-demo:sideBySideBrowserDistribution  # Side-by-side build
```

## Project Structure

```
common/               — Core APIs, layouts, modifiers (foundation equivalent)
material-icons/       — Icon system (core + extended)
material3/            — Material Design 3 components
navigation/           — Navigation support (experimental)
lifecycle-viewmodel/  — ViewModel support (experimental)
demo/                 — Demonstration application
buildSrc/             — Build configuration, dependency versions, convention plugins
```

### Source Set Layout (per module)

- `commonMain/` — Shared APIs (expect declarations)
- `composeUiMain/` — Compose UI targets (JVM, Android, iOS, Wasm) (actual implementations)
- `jsMain/` — JS DOM implementations (Kobweb/HTML) (actual implementations)

### Key Configuration

- Dependency versions: `buildSrc/src/main/kotlin/VersionsAndDependencies.kt`
- Convention plugins: `buildSrc/src/main/kotlin/*.gradle.kts`
- Memory/Kotlin config: `gradle.properties`

## Code Style

Follow the [organization Kotlin code style guide](https://github.com/huanshankeji/.github/blob/main/kotlin-code-style.md).

## Coding Conventions

See [`.github/copilot-instructions.md`](.github/copilot-instructions.md) for detailed coding conventions including:
- Expect/actual patterns for common data types
- Component organization (main package vs ext package vs labs)
- Trailing comma conventions
- Nullable Composable type conventions
- "Copied and adapted" code patterns
- CSS style shortcuts for JS DOM
- Platform-limited parameters documentation
- Adding new components checklist
