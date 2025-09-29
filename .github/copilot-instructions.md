# Copilot Instructions for Compose Multiplatform HTML Unified

## Repository Overview

**Compose Multiplatform HTML Unified** is a Kotlin Multiplatform library that provides unified Compose Multiplatform wrappers of common and Material Design APIs for both **rendering-based Compose UI** (Android, desktop JVM, iOS, web Kotlin/Wasm) and **DOM-based Compose HTML**. This library was previously named "Compose Multiplatform Material".

### Repository Statistics
- **Size**: Medium-large multiplatform project with ~15 modules
- **Language**: Kotlin (multiplatform)
- **Build System**: Gradle with Kotlin DSL
- **Target Platforms**: JVM, Android, iOS, JS (DOM), Wasm-JS (Canvas)
- **Key Dependencies**: Compose Multiplatform, Kobweb Silk, Material Web Components
- **Current Version**: 0.6.0-SNAPSHOT

## Build Instructions and Environment Setup

### Prerequisites
- **JDK 17**: Required (as specified in GitHub Actions workflows)
- **Network Access**: Required for initial builds to download Android Gradle Plugin and Kobweb dependencies
- **Gradle 9.0.0**: Automatically handled by wrapper

### Essential Repository Setup
```bash
# The project depends on Kobweb which requires a specific Maven repository
# This is automatically configured in buildSrc/src/main/kotlin/common-conventions.gradle.kts
# Maven repository: https://us-central1-maven.pkg.dev/varabyte-repos/public
```

### Build Commands (In Order)

#### 1. Basic Project Setup
```bash
./gradlew --version  # Verify Gradle 9.0.0 and JDK 17
```

#### 2. Publishing to Local Maven (Primary Development Command)
```bash
./gradlew publishToMavenLocal
```
**Important**: Always run `publishToMavenLocal` first when making changes, as mentioned in CONTRIBUTING.md. This publishes libraries to your local Maven repository so dependent projects can use your changes.

#### 3. Running Tests and Checks
```bash
./gradlew check
```
**Note**: The project has "limited number of tests" according to CONTRIBUTING.md, but this validates what exists.

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
- **Gradle JVM**: 2GB memory allocated via `org.gradle.jvmargs=-Xmx2G` in gradle.properties (required for Wasm compilation)

#### Common Build Issues and Workarounds

1. **Network Connectivity**: Initial builds require internet access for Android Gradle Plugin (8.10.1) and Kobweb dependencies. If build fails with network errors, ensure internet connectivity.

2. **Memory Issues**: If Wasm compilation fails with OOM, the memory is already optimized in gradle.properties.

3. **Kotlin/JS Store**: The `kotlin-js-store` directory may be generated during JS builds - this should not be committed.

4. **Gradle Daemon**: May timeout on first runs. Use `--no-daemon` flag if needed: `./gradlew check --no-daemon`

## Project Architecture and Layout

### Module Structure
```
├── common/                    # Core APIs, layouts, modifiers (foundation equivalent)
├── material-icons-core/       # Icon system core
├── material2/                 # Material Design 2 components  
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
- **gradle.properties**: JVM memory settings and Kotlin MPP configuration
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
└── iosMain/              # iOS-specific implementations
```

### CI/CD Workflows

#### `.github/workflows/ci.yml`
- **Trigger**: Push to any branch, manual dispatch
- **Platforms**: ubuntu-latest, macos-latest, windows-latest  
- **JDK**: 17-temurin
- **Commands**: Uses `huanshankeji/.github/actions/gradle-test-and-check@v0.2.0`

#### `.github/workflows/demo-gh-pages.yml`
- **Trigger**: Push/PR to `release` branch, manual dispatch
- **Platform**: ubuntu-latest
- **Purpose**: Builds and deploys demo to GitHub Pages
- **Build Command**: `./gradlew :compose-multiplatform-html-unified-demo:sideBySideBrowserDistribution`
- **Artifact Path**: `demo/build/dist/sideBySide/productionExecutable/`

### Dependencies and External Integration

#### Required External Repositories
```kotlin
repositories {
    mavenCentral()
    google()  // For Android components
    maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")  // For Kobweb
}
```

#### Key Dependencies
- **Kotlin**: 2.2.0-RC2 with Compose Compiler
- **Compose Multiplatform**: 1.8.1
- **Android Gradle Plugin**: 8.10.1  
- **Kobweb**: 0.19.2 (not on Maven Central)
- **Compose HTML Material**: 0.4.0
- **Binary Compatibility Validator**: 0.16.3 (enabled)

### Platform-Specific Implementation Patterns

#### Compose UI Platforms (Android, JVM, iOS, Wasm)
- Delegate directly to official `androidx.compose` APIs
- Located in `composeUiMain` source sets

#### JS DOM Platform  
- Built on **Kobweb Silk** (provides Modifier system and layouts)
- Uses **Material Web Components** via Compose HTML Material
- Located in `jsMain` source sets
- Requires CSS imports for Material Icons

### Development Patterns and Conventions

#### API Structure
- **Common APIs**: Defined in `commonMain`, expect/actual pattern for platform differences
- **Ext Components**: Components in `ext` packages don't follow androidx.compose APIs exactly but provide more idiomatic platform-specific wrappers
- **Parameter Naming**: Parameters with "JsDom" or "ComposeUi" suffixes are platform-specific

#### Code Quality and Validation
- **Binary Compatibility**: Enforced via kotlinx.validation plugin
- **Code Style**: IntelliJ IDEA Code Cleanup and Reformat Code applied project-wide
- **Limited Testing**: Project acknowledges "limited number of tests"

### Root Directory Files
```
.gitignore              # Standard exclusions plus .kotlin, local.properties  
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

1. **Build Validation**: Run `./gradlew publishToMavenLocal` to ensure libraries compile
2. **Test Validation**: Run `./gradlew check` to validate existing tests pass  
3. **Demo Validation**: Build demo with `./gradlew :compose-multiplatform-html-unified-demo:sideBySideBrowserDistribution`
4. **CI Simulation**: Test on multiple platforms if possible (the CI runs on Ubuntu, macOS, Windows)
5. **Binary Compatibility**: The kotlinx binary compatibility validator will catch API breaks

## Important Notes for Agents

- **Trust These Instructions**: Only perform additional searches if information here is incomplete or incorrect
- **Network Dependency**: Initial builds require internet access - cannot work in fully offline environments
- **Memory Requirements**: Ensure adequate memory for Wasm compilation (2GB JVM heap configured)
- **Kobweb Dependency**: This is the main external dependency not on Maven Central - builds will fail without access to their repository
- **Limited Test Coverage**: Don't expect comprehensive test suites - focus on build and demo validation
- **Platform Complexity**: This is a sophisticated multiplatform project with 6+ target platforms and complex expect/actual patterns