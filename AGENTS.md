# AGENTS.md

## Overview

**Compose Multiplatform HTML Unified** is a Kotlin Multiplatform library providing unified Compose Multiplatform wrappers of common and Material Design APIs for both **rendering-based Compose UI** (Android, desktop JVM, iOS, web Kotlin/Wasm) and **DOM-based Compose HTML**.

## Build and Test

### Prerequisites

- **JDK 17** (as specified in CI workflows)
- Internet access for initial dependency resolution

**IMPORTANT**: If the project uses snapshot dependencies of other `com.huanshankeji` libraries, especially in a branch other than `main` such as `dev`, refer to the setup instructions at <https://github.com/huanshankeji/.github/blob/main/dev-instructions.md#about-snapshot-dependencies-of-our-library-projects>.

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

### Troubleshooting

- **Network**: Initial builds require internet access for dependency resolution.
- **Memory/OOM**: Wasm compilation requires 4GB+ heap (pre-configured in `gradle.properties`).
- **`kotlin-js-store`**: This directory may be generated during JS builds — commit it when updated automatically.
- **Gradle Daemon timeout**: Use `--no-daemon` flag if needed.

### Visual Validation via Browser Demos (for AI agents)

AI agents with browser automation (e.g., Playwright) can visually validate **both** JS DOM and Wasm JS (Compose UI) rendering using Gradle development servers:

1. **JS DOM** (HTML/CSS rendering): `./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun` — serves at `http://localhost:8080`.
2. **Wasm JS** (Compose UI canvas rendering): `./gradlew :compose-multiplatform-html-unified-demo:wasmJsBrowserDevelopmentRun` — serves at `http://localhost:8080`.

Both targets can run simultaneously (Gradle auto-assigns alternative ports when 8080 is taken). For the Wasm JS target, UI elements are rendered on a canvas inside a shadow DOM — access accessibility nodes through `document.body.shadowRoot` and query `[role="button"]` etc.

**Note**: The JVM desktop demo (`./gradlew :compose-multiplatform-html-unified-demo:run`) requires a GUI display and cannot be run by headless AI agents. Use the Wasm JS target for Compose UI visual validation instead.

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

```
src/
├── commonMain/                  # Shared APIs across all platforms
├── composeUiMain/               # Compose UI targets (JVM, Android, iOS, Wasm)
├── composeUiExceptAndroidMain/  # Compose UI excluding Android
├── androidMain/                 # Android-specific implementations
├── jsMain/                      # JS DOM implementations (Kobweb/HTML)
├── jvmMain/                     # Desktop JVM implementations
├── wasmJsMain/                  # Wasm-JS specific implementations
└── iosMain/                     # iOS-specific implementations
```

Not all modules have all source sets. Library modules typically have `commonMain`, `composeUiMain`, and `jsMain`, while the demo module has platform-specific source sets like `jvmMain`, `wasmJsMain`, and `iosMain`.

### Key Configuration

- Dependency versions: `buildSrc/src/main/kotlin/VersionsAndDependencies.kt`
- Convention plugins: `buildSrc/src/main/kotlin/*.gradle.kts`
- Memory/Kotlin config: `gradle.properties`

### Platform-Specific Implementation Patterns

#### Compose UI Platforms (Android, JVM, iOS, Wasm)
- Delegate directly to official `androidx.compose` APIs
- Located in `composeUiMain` source sets

#### JS DOM Platform
- Built on **Kobweb Silk** (provides Modifier system and layouts)
- Uses **Material Web Components** via Compose HTML Material
- Located in `jsMain` source sets

## Code Style

Follow the [organization Kotlin code style guide](https://github.com/huanshankeji/.github/blob/main/kotlin-code-style.md).

**Trailing Comma Conventions** (following Compose UI source conventions):
- Add trailing commas to the **last parameter** of **multi-line** parameter/argument lists in Composable or Compose-related function **definitions and invocations**. This helps Git track fewer unnecessary changes when parameters are added/removed.
- Do **NOT** add trailing commas when:
  - All parameters/arguments are on a **single line**
  - The function has only a **single parameter** on its own line
  - The parameter list belongs to an **annotation** (e.g., `@Deprecated(...)`, `@RequiresOptIn(...)`)
  - The construct is not Composable or Compose-related (e.g., plain data classes, utility functions)
- When the last parameter has an **end-of-line comment**, place the comma **before** the comment:
  ```kotlin
  // Correct:
  actionOnNewLine: Boolean = false, // comment
  // Incorrect:
  actionOnNewLine: Boolean = false // comment,
  ```

**Nullable Composable Type Conventions** (following Compose UI conventions):
- Use `@Composable ((*) -> Unit)?` for nullable composable parameter types (Compose UI convention)
- Not `(@Composable (*) -> Unit)?` (Compose HTML convention — do not use)
- Exception: Extension function receivers require parentheses: `fun (@Composable (Modifier) -> Unit).foo()`

**Null Check Conventions:**
- Use `!= null` instead of `!== null` and `== null` instead of `=== null` (idiomatic Kotlin; the compiler auto-translates `== null` to referential equality)

## API Change Policy

> **Do NOT run `apiDump` automatically** — even if `check` fails due to `apiCheck` failures (because public APIs have changed). Leave `apiDump` for the human developer to run after they have reviewed the API changes.
>
> Only run `apiDump` if you are **very confident** you have completely and correctly finished all the task goals and no further API edits from the developer will be needed.

When `check` fails solely due to `apiCheck` failures, use the following commands for validation instead:
```bash
./gradlew publishToMavenLocal
./gradlew :compose-multiplatform-html-unified-demo:run        # if the run task exists for desktop JVM
./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun  # for JS browser
```

## Coding Conventions

### Expect/Actual Patterns for Common Data Types

Choose the `expect` declaration based on the corresponding type in `androidx.compose.material3`:

- **`expect interface`**: Use when the corresponding platform type is an `interface` (e.g., `SnackbarVisuals`,
  `SnackbarData`). This avoids wrapping stacking up when converting to/from platform types:
  - `actual typealias` on Compose UI platforms — the platform type directly satisfies the interface.
  - `actual interface` on JS DOM platform — with local implementations (e.g., private inner classes within
    `SnackbarHostState`).
- **`expect enum class`**: Use when the corresponding platform type is an `enum class` (e.g., `SnackbarResult`,
  `SnackbarDuration`):
  - `actual typealias` on Compose UI platforms.
  - `actual enum class` on JS DOM platform.
- **`expect class`**: Use when the corresponding Compose UI type is a `class`, or when the common `expect` members
  differ from the platform type due to design requirements caused by platform differences (e.g.,
  `DropdownMenuBoxScope` includes `fun Modifier.menuAnchorJsDom()` which doesn't exist in the Compose UI counterpart),
  or when the type cannot be actualized via typealias for other reasons (e.g., `SnackbarHostState` has default argument
  values in its members):
  - The `actual class` on Compose UI platforms wraps the platform value directly (e.g.,
    `actual class SnackbarHostState(val platformValue: PlatformSnackbarHostState)`).
  - The `actual class` on JS DOM platform provides its own implementation.

Non-expect `class`, `interface`, or `enum class` can also be used for types not covered by the patterns above.

### Component Organization Patterns

1. **Main package** (`com.huanshankeji.compose.material3`): Components that can be unified following Compose UI APIs
   - Mandatory parameters must be equivalent to those of the original Compose UI component
   - Common optional parameters should have the same type
       - An exception: nullability differences with same content semantics. Example: `content: (() -> Unit)? = null` and
         `content: () -> Unit = {}` are considered equivalent.
   - Example: `RadioButton`, `Slider`, `AlertDialog`

2. **Ext package** (`com.huanshankeji.compose.material3.ext`): Components with platform-specific APIs
   - Use when unification would compromise usability or when platforms have fundamentally different UX patterns
   - Especially when the JS DOM component only supports taking a text in an HTML attribute such as `value` as its content, while the Compose one takes a `@Composable` content block
   - Example: `FilledSelect`, `OutlinedSelect` (different interaction models between ExposedDropdownMenuBox on Compose UI and native select on JS)

3. **Labs annotations** instead of labs package:
   - Mark JS implementations with `@MaterialWebLabsApi` when they depend on Material Web labs components
   - Opt-in to `@MaterialWebLabsApi` if Compose UI visual effects can already be achieved with consistency on JS DOM

### Platform-Limited Parameters

Some parameters only work on certain platforms (typically only on Compose UI and not on JS DOM). When this is the case:
- Document it in KDoc with `@param paramName currently only working on Compose UI.`
- Add an inline comment in the JS DOM implementation explaining why it's not passed (e.g., `// not passed here, not sure whether it's supported by the underlying component`)
- Examples: `enabled` in tabs, `space` in segmented button rows

### Unsupported Compose UI APIs

When a common API cannot support all parameters, values, or members from the corresponding Compose UI API (e.g., because
a required type has not been ported yet, or because there is no equivalent on JS DOM), add the unsupported items as
**commented-out lines** in the common `expect` declaration, with a brief reason in a comment explaining why each is not
yet supported. Also list them in a KDoc block at the top of the declaration. This applies to:
- **Parameters** of composable functions or constructors
- **Values/constants** in companion objects (e.g., unsupported `TextOverflow.StartEllipsis`)
- **Member functions** or properties that cannot yet be unified

This makes it easy to see what's missing and why, and serves as a reference for future implementation.
```kotlin
/**
 * The following Compose UI parameters are not yet supported:
 * - `fontFamily: FontFamily?` — requires porting `FontFamily`
 * - `onTextLayout: ((TextLayoutResult) -> Unit)?` — requires porting `TextLayoutResult`
 */
@Composable
expect fun Text(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight? = null,
    // fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    // onTextLayout: ((TextLayoutResult) -> Unit)? = null,
)
```

### "Copied and Adapted" Code

When adding new common types or APIs that correspond to Compose UI types, always **start from the original Compose UI
source code** — copy the relevant parts and adapt them for the common/JS DOM implementation. Do not write implementations
from scratch, as this leads to inconsistencies in API surface, member ordering, and behavior.

The original Compose UI source for a given version can be found on GitHub, e.g.:
`https://raw.githubusercontent.com/JetBrains/compose-multiplatform-core/v<COMPOSE_MULTIPLATFORM_VERSION>/compose/ui/ui-text/src/commonMain/kotlin/androidx/compose/ui/text/style/TextDecoration.kt`
where `<COMPOSE_MULTIPLATFORM_VERSION>` should match the Compose Multiplatform version used by this project (see `buildSrc/build.gradle.kts` for the current version).

When copying and adapting, ensure:
1. The supported APIs match the original source (same names, types, and semantics).
2. The definition order is consistent with the original.
3. Unsupported APIs are documented per the pattern above.

These implementations should have a comment like:
```kotlin
// Copied and adapted from `ComponentName` in `androidx.compose.ui.text.style`. Do not edit without referencing to the original corresponding implementation.
```

**Exceptions to Compose UI Design Replication:**
In some cases, the JS DOM implementation may intentionally deviate from the Compose UI internal design
when the HTML/CSS platform provides a more natural mapping. For example, `AnnotatedString.Builder` on
JS DOM uses a nestable tree/node design (mapping `withStyle` calls to nested `<span>` elements) instead
of replicating the Compose UI range-based (`pushStyle`/`addStyle`/`pop`) design. When such exceptions
are made, document the rationale in the source file and note the commit of the dropped design for reference.

### CSS Style Shortcuts

In JS DOM implementations, prefer type-safe CSS extension shortcuts over raw `property()` calls. For example:
- Use `overflow(Overflow.Hidden)` instead of `property("overflow", "hidden")`
- Use `minHeight("${minLines}lh")` instead of `property("min-height", "${minLines}lh")`
- These shortcuts may come from Compose HTML, Kobweb, or
  [compose-html-common](https://github.com/huanshankeji/compose-html-material/tree/main/compose-html-common).
  If a type-safe shortcut does not exist, using `property()` is acceptable but should be noted for potential
  future contribution to upstream libraries.

### `fillMax*Stretch` vs `fillMax*`

On JS DOM, prefer `fillMaxWidthStretch()` / `fillMaxHeightStretch()` / `fillMaxSizeStretch()` over `fillMaxWidth()` / `fillMaxHeight()` / `fillMaxSize()` when there is no `fraction` parameter. The `fillMax*` modifiers set CSS `width`/`height: 100%`, which causes overflow (and unnecessary scrollbars) when the element or an ancestor has padding or margin. The `fillMax*Stretch` variants use the CSS `stretch` value (with `-webkit-fill-available` fallback), which correctly fills the available space without overflowing. Only use `fillMax*` with the `fraction` parameter when you need a fractional size (e.g., `fillMaxWidth(0.5f)`), as `fillMax*Stretch` does not support fractions.

### Avoiding Duplicate Components

Before adding a new component to the demo or a new wrapper, always search the existing codebase to verify it doesn't already exist. For example, if `List` with `ListItem` is already implemented via `ListScope.ListItem`, do not add a second `List` component. Check both the main package and the `ext` package.

## Adding New Components

When adding a new component to the library, follow these additional steps:

1. **Add to Demo**: Every new component **must** be added to the Material 3 demo page (`demo/src/commonMain/kotlin/com/huanshankeji/compose/material/demo/Material3.kt`). This serves as both a visual test and a usage example. Add a clearly labelled section with realistic usage.

2. **Visual Consistency**: After adding to the demo, compare the rendering between **Compose UI** (Wasm/JVM) and **JS DOM** platforms using the side-by-side demo (`./gradlew :compose-multiplatform-html-unified-demo:sideBySideBrowserDistribution`). Improve visual consistency as much as possible. Common areas to align:
   - Component sizing and spacing
   - Positioning of slots (icons, labels, actions)
   - Color and shape treatment
   - State handling (enabled/disabled/selected/error)

3. Note that some platform differences are inherent to the underlying components (Material Web vs Compose UI) and cannot be fully eliminated. Document any known differences in KDoc or inline comments.

4. **README update**: Add the new component to the supported API catalog in README.md
