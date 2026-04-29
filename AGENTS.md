# AGENTS.md

This file provides guidance to AI coding agents when working with code in this repository.

## Prerequisites

- **JDK 17** required (see GitHub Actions workflows for reference)

## Build Commands

```bash
./gradlew publishToMavenLocal    # Primary build validation — always run first
./gradlew check                  # Run tests and API compatibility checks
```

**If `check` fails solely due to `apiCheck`**: Do NOT run `apiDump` automatically. Validate with `publishToMavenLocal` and demo runs instead, then leave `apiDump` for the human developer after reviewing API changes.

**Note**: The `kotlin-js-store` directory may be updated during JS builds — commit changes when updated automatically by Gradle.

### Demo Validation

```bash
./gradlew :compose-multiplatform-html-unified-demo:run                        # Desktop JVM (requires GUI)
./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun    # JS DOM in browser
./gradlew :compose-multiplatform-html-unified-demo:wasmJsBrowserDevelopmentRun # Wasm/Compose UI in browser
./gradlew :compose-multiplatform-html-unified-demo:sideBySideBrowserDistribution  # Side-by-side comparison
```

### Snapshot Dependencies

If working on a branch other than `main` that uses snapshot dependencies of other `com.huanshankeji` libraries, see https://github.com/huanshankeji/.github/blob/main/dev-instructions.md#about-snapshot-dependencies-of-our-library-projects

## Architecture

**Kotlin Multiplatform library** providing unified Compose wrappers for rendering-based Compose UI (Android, desktop, iOS, Wasm) and DOM-based Compose HTML.

### Module Structure

| Module | Purpose |
|--------|---------|
| `common` | Core APIs, layouts, modifiers (foundation equivalent) |
| `material3` | Material Design 3 components |
| `material-icons/core`, `material-icons/extended` | Material icon system |
| `navigation` | Navigation support (experimental) |
| `lifecycle-viewmodel` | ViewModel support (experimental) |
| `material2` | MD2 components (broken/unmaintained, kept for reference, not published) |
| `demo` | Demonstration app for all platforms |
| `buildSrc` | Convention plugins and dependency versions |

### Source Set Hierarchy

Each library module typically has:
- `commonMain` — shared expect declarations
- `composeUiMain` — actual implementations delegating to `androidx.compose` (JVM, Android, iOS, Wasm)
- `jsMain` — actual implementations using Kobweb Silk + Material Web via Compose HTML

The custom `composeUi` source set group is defined in `common-conventions.gradle.kts` via `applyDefaultHierarchyTemplate`.

### Key Config Files

- `buildSrc/src/main/kotlin/VersionsAndDependencies.kt` — all dependency versions
- `buildSrc/src/main/kotlin/common-conventions.gradle.kts` — shared Kotlin multiplatform build config
- `buildSrc/src/main/kotlin/lib-conventions.gradle.kts` — library publishing config
- `gradle.properties` — JVM memory (4GB for Wasm), Kotlin MPP settings

### Platform Implementation Strategy

- **Compose UI platforms**: Delegate to official `androidx.compose` APIs via `actual typealias` or wrapper classes
- **JS DOM platform**: Built on Kobweb Silk (Modifier system, layouts) + Material Web Components via compose-html-material

## Code Style

Follow https://github.com/huanshankeji/.github/blob/main/kotlin-code-style.md

**Trailing commas**: Add to the last parameter of multi-line Composable parameter/argument lists. Do NOT add for single-line lists, single parameters, or annotations.

**Nullable Composable types**: Use `@Composable (() -> Unit)?` (Compose UI convention), not `(@Composable () -> Unit)?`.

**Null checks**: Use `== null` / `!= null` (not `===`/`!==`).

## API Design Patterns

### Expect/Actual Type Selection

Choose based on the corresponding `androidx.compose.material3` type:
- `expect interface` → when platform type is interface (use `actual typealias` on Compose UI, `actual interface` on JS)
- `expect enum class` → when platform type is enum
- `expect class` → when members differ across platforms or typealias isn't possible (wrap platform value on Compose UI)

### Component Organization

- **Main package** (`com.huanshankeji.compose.material3`): Components unifiable following Compose UI APIs
- **Ext package** (`com.huanshankeji.compose.material3.ext`): Platform-specific wrappers that can't follow Compose UI APIs exactly

### Unsupported APIs

Document as commented-out lines in expect declarations with KDoc explaining why:

```kotlin
/**
 * Not yet supported:
 * - `fontFamily` — requires porting `FontFamily`
 */
@Composable
expect fun Text(
    text: String,
    // fontFamily: FontFamily? = null,
)
```

### Platform-Limited Parameters

Some parameters only work on certain platforms (typically only Compose UI, not JS DOM):
- Document in KDoc: `@param paramName currently only working on Compose UI.`
- Add inline comment in JS DOM implementation explaining why it's not passed

### Exceptions to Compose UI Design Replication

In some cases, the JS DOM implementation may intentionally deviate from the Compose UI internal design when HTML/CSS provides a more natural mapping (e.g., using nested `<span>` elements instead of replicating a range-based design). When such exceptions are made, document the rationale in the source file and note the commit of the dropped design for reference.

### "Copied and Adapted" Code

Always start from original Compose UI source (from `JetBrains/compose-multiplatform-core`), adapt for common/JS. Add comment:
```kotlin
// Copied and adapted from `X` in `androidx.compose.y.z`. Do not edit without referencing to the original corresponding implementation.
```

## Adding New Components

When adding or aligning components, search https://m3.material.io/ to find the component first. Each component's overview page usually links to both its Jetpack Compose implementation and Material Web implementation.

1. Implement in `commonMain` (expect), `composeUiMain` (actual), `jsMain` (actual)
2. Add to demo in `demo/src/commonMain/kotlin/com/huanshankeji/compose/material/demo/Material3.kt`
3. Compare rendering between Compose UI and JS DOM via side-by-side demo (see `demo/AGENTS.md` for browser automation details)
4. Update README.md API catalog
5. Mark JS labs dependencies with `@MaterialWebLabsApi`

## JS DOM Notes

- Prefer `fillMaxWidthStretch()` over `fillMaxWidth()` — avoids overflow from padding/margin (uses CSS `stretch` instead of `100%`)
- Prefer type-safe CSS shortcuts over raw `property()` calls
- Search existing codebase before adding components to avoid duplicates
