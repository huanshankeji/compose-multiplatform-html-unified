# Copilot Instructions for Compose Multiplatform HTML Unified

## Repository Overview

**Compose Multiplatform HTML Unified** is a Kotlin Multiplatform library that provides unified Compose Multiplatform wrappers of common and Material Design APIs for both **rendering-based Compose UI** (Android, desktop JVM, iOS, web Kotlin/Wasm) and **DOM-based Compose HTML**. This library was previously named "Compose Multiplatform Material".

### Repository Statistics
- **Size**: Medium-large multiplatform project with ~8 modules (common, material-icons-core, material2, material3, navigation, lifecycle-viewmodel, demo, plus buildSrc)
- **Language**: Kotlin (multiplatform)
- **Build System**: Gradle with Kotlin DSL
- **Target Platforms**: JVM, Android, iOS, JS (DOM), Wasm-JS (Canvas)
- **Key Dependencies**: Compose Multiplatform, Kobweb Silk, Material Web Components

## Build Instructions and Environment Setup

### Prerequisites
- **JDK 17**: Required (as specified in GitHub Actions workflows)
- **Network Access**: Required for initial builds to download Android Gradle Plugin and other dependencies
- **Gradle 9.4.1**: Automatically handled by wrapper

**IMPORTANT**: Initial builds require internet access for dependency resolution. Once dependencies are cached, subsequent builds can use `--offline` flag if needed.

**IMPORTANT**: If the project uses snapshot dependencies of other `com.huanshankeji` libraries, especially in a branch other than `main` such as `dev`, refer to the setup instructions at <https://github.com/huanshankeji/.github/blob/main/dev-instructions.md#about-snapshot-dependencies-of-our-library-projects>.

### Build Commands (In Order)

#### 1. Basic Project Setup
```bash
./gradlew --version  # Verify Gradle and JDK
```

#### 2. Publishing to Local Maven (Primary Development Command)
```bash
./gradlew publishToMavenLocal
```
**Important**: Always run `publishToMavenLocal` first when making changes, as mentioned in CONTRIBUTING.md. This ensures libraries compile correctly and validates the build process.

#### 3. Running Tests and Checks (Essential Commands - validated and working)
```bash
./gradlew check
```
**Note**: The project has "limited number of tests" according to CONTRIBUTING.md, but this validates what exists.

> **If `check` fails solely due to `apiCheck` failures** (because public APIs have changed and the `.klib.api` files are out of date), do **not** run `apiDump` automatically. Instead, validate using:
> ```bash
> ./gradlew publishToMavenLocal
> ./gradlew :compose-multiplatform-html-unified-demo:run        # interactive validation on desktop JVM
> ./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun  # interactive validation in JS browser
> ```
> Then leave the `apiDump` step to the human developer to perform after reviewing API changes. The `demo` module supports JVM desktop (`run`), JS (`jsBrowserDevelopmentRun`), Android, iOS, and wasmJs targets.

#### 4. Building Demo Applications
```bash
# Build the side-by-side demo (used for GitHub Pages)
./gradlew :compose-multiplatform-html-unified-demo:sideBySideBrowserDistribution

# Individual platform builds
./gradlew jsBrowserDistribution    # JS DOM version
./gradlew wasmJsBrowserDistribution  # Wasm-JS Canvas version
```

### Build Configuration Details

#### Memory Requirements

- **Gradle JVM**: 4GB memory allocated via `org.gradle.jvmargs=-Xmx4G` in gradle.properties (required for Wasm
  compilation)

#### Common Build Issues and Workarounds

1. **Network Connectivity**: Initial builds require internet access for Android Gradle Plugin (8.12.3) and other dependencies. If build fails with network errors, ensure internet connectivity.

2. **Memory Issues**: If Wasm compilation fails with OOM, the memory is already optimized in gradle.properties.

3. **Kotlin/JS Store**: The `kotlin-js-store` directory may be generated during JS builds - this should be committed when updated automatically by Gradle commands.

4. **Gradle Daemon**: May timeout on first runs. Use `--no-daemon` flag if needed: `./gradlew check --no-daemon`

### Build Timing and Known Issues

**Timing Expectations**:
- First build: 5-10 minutes (includes dependency resolution and Wasm compilation)
- Subsequent builds: 1-3 minutes
- `publishToMavenLocal`: 3-5 minutes
- `check` execution: 2-5 minutes (limited tests)
- Demo builds: 2-4 minutes

**Common Issues**:
- **Wasm Compilation**: Requires 4GB+ heap memory (pre-configured in gradle.properties)
- **Network Dependencies**: Initial builds require internet access for dependency resolution
- **Platform-specific builds**: Some targets may be disabled on certain OS (use `--continue` flag)

## Project Architecture and Layout

### Module Structure
```
├── common/                    # Core APIs, layouts, modifiers (foundation equivalent)
├── material-icons-core/       # Icon system core
├── material2/                 # Material Design 2 components (broken with KMDC's old dependencies, kept for reference, not published)
├── material3/                 # Material Design 3 components
├── navigation/               # Navigation support (experimental)
├── lifecycle-viewmodel/      # ViewModel support (experimental)
├── demo/                     # Demonstration application
└── buildSrc/                 # Build configuration and dependencies
```

### Key Configuration Files
- **buildSrc/src/main/kotlin/VersionsAndDependencies.kt**: All dependency versions
- **buildSrc/src/main/kotlin/common-conventions.gradle.kts**: Shared build configuration
- **buildSrc/src/main/kotlin/lib-conventions.gradle.kts**: Library-specific configuration
- **gradle.properties**: JVM memory settings for Gradle (increase if OOM occurs) and Kotlin MPP configuration
- **settings.gradle.kts**: Project structure and repository configuration

### Source Set Structure (Per Module)
```
src/
├── commonMain/           # Shared APIs across all platforms
├── composeUiMain/        # Compose UI targets (JVM, Android, iOS, Wasm)
├── composeUiExceptAndroidMain/  # Compose UI excluding Android
├── androidMain/          # Android-specific implementations
├── jsMain/               # JS DOM implementations (Kobweb/HTML)
├── jvmMain/              # Desktop JVM implementations
├── wasmJsMain/           # Wasm-JS specific implementations
└── iosMain/              # iOS-specific implementations
```
Not all modules have all source sets. For example, library modules typically have `commonMain`, `composeUiMain`, and `jsMain`, while the demo module has platform-specific source sets like `jvmMain`, `wasmJsMain`, and `iosMain`.

### Dependencies and External Integration

#### Required External Repositories
```kotlin
repositories {
    mavenCentral()
    google()  // For Android components
}
```

**Note**: All dependencies are available from standard repositories. If working with snapshot dependencies of other `com.huanshankeji` libraries, additional setup may be required as per organization instructions.

#### Key Dependencies

- **Kotlin**: 2.3.20 with Compose Compiler
- **Compose Multiplatform**: 1.10.3
- **Android Gradle Plugin**: version defined in `buildSrc/build.gradle.kts` (use a stable, officially released 8.x
  version)
- **Kobweb**: 0.24.0 (now on Maven Central)
- **Compose HTML Material**: 0.5.0
- **Binary Compatibility Validator**: 0.18.1 (enabled)

### Platform-Specific Implementation Patterns

#### Compose UI Platforms (Android, JVM, iOS, Wasm)
- Delegate directly to official `androidx.compose` APIs
- Located in `composeUiMain` source sets

#### JS DOM Platform  
- Built on **Kobweb Silk** (provides Modifier system and layouts)
- Uses **Material Web Components** via Compose HTML Material
- Located in `jsMain` source sets
- Requires CSS imports for Material Icons

**`fillMax*Stretch` vs `fillMax*`**: On JS DOM, prefer `fillMaxWidthStretch()` / `fillMaxHeightStretch()` / `fillMaxSizeStretch()` over `fillMaxWidth()` / `fillMaxHeight()` / `fillMaxSize()` when there is no `fraction` parameter. The `fillMax*` modifiers set CSS `width`/`height: 100%`, which causes overflow (and unnecessary scrollbars) when the element or an ancestor has padding or margin. The `fillMax*Stretch` variants use the CSS `stretch` value (with `-webkit-fill-available` fallback), which correctly fills the available space without overflowing. Only use `fillMax*` with the `fraction` parameter when you need a fractional size (e.g., `fillMaxWidth(0.5f)`), as `fillMax*Stretch` does not support fractions.

#### Supported Material 3 Components

For an up-to-date list of supported unified component APIs, refer to README.md.

When adding or aligning components, you can search in <https://m3.material.io/> to find the component first. On each component's overview page, you can usually find links or references to both its Jetpack Compose implementation (almost identical to Compose Multiplatform's Compose UI APIs) and its web/Material Web implementation.

### Development Patterns and Conventions

#### API Structure
- **Common APIs**: Defined in `commonMain`, expect/actual pattern for platform differences
- **Ext Components**: Components in `ext` packages don't follow androidx.compose APIs exactly but provide more idiomatic platform-specific wrappers
- **Parameter Naming**: Parameters with "JsDom" or "ComposeUi" suffixes are platform-specific

#### Code Quality and Validation
- **Binary Compatibility**: Enforced via org.jetbrains.kotlinx.binary-compatibility-validator plugin
- **Limited Testing**: Project acknowledges "limited number of tests"

#### API Change Policy

> **Do NOT run `apiDump` automatically** — even if `check` fails due to `apiCheck` failures (because public APIs have changed). Leave `apiDump` for the human developer to run after they have reviewed the API changes. Running `apiDump` automatically generates unnecessary Git-tracked churn before the developer has had a chance to review the API surface.
>
> Only run `apiDump` if you are **very confident** you have completely and correctly finished all the task goals and no further API edits from the developer will be needed.

When `check` fails solely due to `apiCheck` failures, use the following commands for validation instead:
```bash
./gradlew publishToMavenLocal
./gradlew :compose-multiplatform-html-unified-demo:run        # if the run task exists for desktop JVM
./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun  # for JS browser
```
Then leave the `apiDump` step to the human developer to perform after reviewing API changes.

**Code Style:**
- Follow [our Kotlin code style guide](https://github.com/huanshankeji/.github/blob/main/kotlin-code-style.md) for all Kotlin code contributions

**Trailing Comma Conventions** (following Compose UI source conventions):
- Add trailing commas to the **last parameter** of **multi-line** parameter/argument lists in Composable or Compose-related function **definitions and invocations**. This helps Git track fewer unnecessary changes when parameters are added/removed.
- Do **NOT** add trailing commas when:
  - All parameters/arguments are on a **single line** (trailing commas don't help Git tracking in this case)
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

#### Architecture Notes
- **Multi-module**: Each feature area is a separate Gradle subproject
- **Expect/Actual Pattern**: Platform-specific implementations using Kotlin Multiplatform patterns
- **Convention Plugins**: Custom build logic in `buildSrc` for consistency across modules
- **Target Platforms**: Sophisticated setup targeting 6+ platforms with different implementation strategies

**Use Expect/Actual Patterns for Common Data Types:**

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

**Component Organization Patterns:**
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

**Platform-Limited Parameters:**
Some parameters only work on certain platforms (typically only on Compose UI and not on JS DOM). When this is the case:
- Document it in KDoc with `@param paramName currently only working on Compose UI.`
- Add an inline comment in the JS DOM implementation explaining why it's not passed (e.g., `// not passed here, not sure whether it's supported by the underlying component`)
- Examples: `enabled` in tabs, `space` in segmented button rows

**Unsupported Compose UI APIs:**
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
    // fontFamily: FontFamily? = null,
    // onTextLayout: ((TextLayoutResult) -> Unit)? = null,
)
```

**"Copied and Adapted" Code:**
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
This prevents casual modifications that could break the carefully adapted logic.

**CSS Style Shortcuts:**
In JS DOM implementations, prefer type-safe CSS extension shortcuts over raw `property()` calls. For example:
- Use `overflow(Overflow.Hidden)` instead of `property("overflow", "hidden")`
- Use `minHeight("${minLines}lh")` instead of `property("min-height", "${minLines}lh")`
- These shortcuts may come from Compose HTML, Kobweb, or
  [compose-html-common](https://github.com/huanshankeji/compose-html-material/tree/main/compose-html-common).
  If a type-safe shortcut does not exist, using `property()` is acceptable but should be noted for potential
  future contribution to upstream libraries.

**Avoiding Duplicate Components:**
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

### Root Directory Files
```
.gitignore              # Standard exclusions plus .kotlin, local.properties  
AGENTS.md               # Points to copilot-instructions.md
build.gradle.kts        # Root build file with binary compatibility validator
CHANGELOG.md            # Detailed version history with breaking changes
CODE_OF_CONDUCT.md      # Standard code of conduct
CONTRIBUTING.md         # Development guidelines and contribution process
gradle.properties       # Memory settings and Kotlin configuration
LEGACY_README.md        # Previous project name reference
LICENSE                 # Apache 2.0 license
README.md               # Comprehensive API documentation and usage guide
settings.gradle.kts     # Project structure and dependency management
```

## Validation Steps for Changes

1. **Build Validation**: Run `./gradlew build` or `./gradlew publishToMavenLocal` to ensure libraries compile
2. **Test Validation**: Run `./gradlew check` to validate existing tests pass. If `check` fails **solely** due to `apiCheck` failures, see the [API Change Policy](#api-change-policy) above — do **not** run `apiDump` automatically.
3. **Demo Validation**: Build demo with `./gradlew :compose-multiplatform-html-unified-demo:sideBySideBrowserDistribution`
4. **Interactive Demo Validation**: Use `./gradlew :compose-multiplatform-html-unified-demo:run` (desktop JVM) or `./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun` (JS browser) to interactively validate Compose UI rendering
5. **CI Simulation**: Test on multiple platforms if possible (the CI runs on Ubuntu, macOS, Windows)
6. **Binary Compatibility**: The kotlinx binary compatibility validator will catch API breaks — run `./gradlew apiDump` **only** when you are very confident all task goals are complete and no further API edits will be needed

### Visual Validation via Browser Demos (for AI agents)

AI agents with browser automation (e.g., Playwright) can visually validate **both** JS DOM and Wasm JS (Compose UI) rendering using Gradle development servers:

1. **JS DOM** (HTML/CSS rendering): Run `./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun` — serves at `http://localhost:8080`.
2. **Wasm JS** (Compose UI canvas rendering): Run `./gradlew :compose-multiplatform-html-unified-demo:wasmJsBrowserDevelopmentRun` — serves at `http://localhost:8080`.

Navigate to the URL shown in the Gradle task output (typically `http://localhost:8080` unless that port is taken) in the automated browser, interact with the UI (click buttons, trigger snackbars, etc.), and take screenshots to verify visual results.

Both targets can run simultaneously — when port 8080 is already taken, Gradle automatically assigns an alternative (e.g., 8081). Check the Gradle task output for the actual port each server is using.

For the Wasm JS target, UI elements are rendered on a canvas inside a shadow DOM. To interact with them via Playwright, access accessibility nodes through `document.body.shadowRoot` and query `[role="button"]` etc.

This allows the agent to iterate on fixes — making code changes, rebuilding, refreshing the browser, and verifying visual output — in a loop. Use this pattern to fix visual bugs and verify snackbar positioning, scrollbar behavior, layout issues, etc. **Use both targets** to compare visual consistency between JS DOM and Compose UI rendering.

**Note**: The JVM desktop demo (`./gradlew :compose-multiplatform-html-unified-demo:run`) requires a GUI display and cannot be run by headless AI agents. Use the Wasm JS target for Compose UI visual validation instead. When headless JVM desktop demo support becomes available in the future, use it alongside the browser demos.

## Important Notes for Agents

- **Trust These Instructions**: Only perform additional searches if information here is incomplete or incorrect
- **Network Dependency**: Initial builds require internet access for dependency resolution
- **Memory Requirements**: Ensure adequate memory for Wasm compilation (4GB JVM heap configured)
- **Limited Test Coverage**: Don't expect comprehensive test suites - focus on build and demo validation
- **Platform Complexity**: This is a sophisticated multiplatform project with 6+ target platforms and complex expect/actual patterns

**Trust these instructions**: This information has been validated through actual command execution and file inspection. Only search for additional information if these instructions are incomplete or found to be incorrect.
