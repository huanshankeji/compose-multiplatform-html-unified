# Compose Multiplatform HTML Unified

[![Maven Central](https://img.shields.io/maven-central/v/com.huanshankeji/compose-multiplatform-html-unified-common)](https://search.maven.org/search?q=g:com.huanshankeji%20AND%20a:compose-multiplatform-html-unified-*)
![Kotlin version](https://kotlin-version.aws.icerock.dev/kotlin-version?group=com.huanshankeji&name=compose-multiplatform-html-unified-common)

Unified Compose Multiplatform wrappers of common and **Material Design** APIs for **rendering-based Compose UI** (officially supported on Android, desktop (JVM), iOS, and web (Kotlin/Wasm)) and **DOM-based Compose HTML**

This library was previously named "Compose Multiplatform Material".

## The scope and implementations of the APIs provided

We try to provide a set of common APIs including composable component APIs akin to those in `androidx.compose` (`androidx.compose.foundation`, `androidx.compose.material`, and `androidx.compose.material3`), meanwhile making them compatible with the Compose HTML APIs. However, only subsets of the composables and composable parameters are supported due to the underlying API differences, limitations of the JS platform and the available Compose HTML composables this project depends on, and our limited effort.

The modules of this library correspond to the Compose Multiplatform modules (or Compose modules in AndroidX) that are not available for Compose HTML, aka those that depend on [the UI module](https://github.com/JetBrains/compose-multiplatform-core/tree/jb-main/compose/ui), more specifically [`ui-graphics`](https://github.com/JetBrains/compose-multiplatform-core/tree/jb-main/compose/ui/ui-graphics). The `common` module of this library corresponds to the `ui` and `foundation` modules, offering UI components (including layouts), modifiers, UI configuration classes, etc.

The Compose HTML / JS DOM source is mainly based on [Kobweb Silk](https://github.com/varabyte/kobweb?tab=readme-ov-file#silk) [Compose](https://github.com/varabyte/kobweb/tree/main/frontend/kobweb-compose), [KMDC](https://github.com/mpetuska/kmdc), and [Compose HTML Material](https://github.com/huanshankeji/compose-html-material) (which is then based on [Material Web](https://github.com/material-components/material-web)).

## References and limitations

Complete visual consistency across different platforms is not guaranteed. See [the side-by-side demo site](https://huanshankeji.github.io/compose-multiplatform-html-unified/demo/) for the visual effects and their differences.

This project is still in development and has not reached a stable state. Some APIs are subject to change and there is no detailed documentation yet. Check out [the demo project source](demo) on how to use the components in addition to the sections below.

## Supported API catalog

### Components

#### Foundation components

- `BasicText`

##### `ext` components

- `TaglessBasicText`

##### Layouts

- `Box` (based on Kobweb)
- `Column` (via flexbox on JS, based on Kobweb)
- `Row` (via flexbox on JS, based on Kobweb)
- `Spacer`

###### `ext` layouts

- `BoxWithConstraints`

##### Lazy

- `LazyColumn` (via flexbox on JS, based on Kobweb)
- `LazyRow` (via flexbox on JS, based on Kobweb)

#### Material 2 components

**Deprecation notice:** The Material 2 components are no longer maintained and published for release since v0.6.0, due to its decreasing popularity and the underlying KMDC library's incompatible Kotlin and Compose versions.

- Button: `Button`
- Card: `Card`
- Checkbox: `Checkbox`
- Divider: `Divider` (not working properly on JS yet)
- Icon: `Icon`
- Icon button: `IconButton`
- Snackbar: `Snackbar` (inconsistent, not recommended), `SnackBarHost` (recommended)
- Switch: `Switch`
- Text: `Text`

##### `ext` components

- Button: `Button`
- Icon button: `IconButton`
- Radio button: `RadioRow`, `RadioGroupRow`
- Switch: `SwitchWithLabel`
- Text: `MaterialText`, `TaglessText`
- Text field: `TextField`, `OutlinedTextField`
- Top app bar: `TopAppBarScaffold`

##### `lazy.ext` components

- `List`/`LazyColumnList` (visually inconsistent for now)

#### Material 3 components

- Button: `Button` (`FilledButton`), `ElevatedButton`, `FilledTonalButton`, `OutlinedButton`, `TextButton`
- Card: `Card` (`FilledCard`), `ElevatedCard`, `OutlinedCard`
- Checkbox: `Checkbox`
- Dialog: `AlertDialog`, `SimpleDialog`
- Divider: `HorizontalDivider`
- Floating action button: `FloatingActionButton`, `SmallFloatingActionButton`, `LargeFloatingActionButton`,
  `ExtendedFloatingActionButton`
- Icon: `Icon`
- Icon button: `IconButton`, `IconToggleButton`, `FilledIconButton`, `FilledIconToggleButton`, `FilledTonalIconButton`,
  `FilledTonalIconToggleButton`, `OutlinedIconButton`, `OutlinedIconToggleButton`
- Navigation drawer: `ModalNavigationDrawer` (deprecated)
- Progress indicator: `LinearProgressIndicator`, `CircularProgressIndicator`
- Radio button: `RadioButton`
- Segmented button: `SingleChoiceSegmentedButtonRow`, `MultiChoiceSegmentedButtonRow`
- Slider: `Slider`, `RangeSlider`
- Snackbar: `SnackbarHost`, `SnackbarHostState`
- Switch: `Switch`
- Tab: `PrimaryTabRow`, `SecondaryTabRow` (tabs are in `ext`)
- Text: `Text`

##### `ext` components

- Badge: `Badge` (deprecated)
- Button: `Button` (`FilledButton`), `ElevatedButton`, `FilledTonalButton`, `OutlinedButton`, `TextButton`
- Card: `Card` (`FilledCard`), `ElevatedCard`, `OutlinedCard`
- Chip: `AssistChip`, `ElevatedAssistChip`, `FilterChip`, `ElevatedFilterChip`, `InputChip`, `SuggestionChip`,
  `ElevatedSuggestionChip`
- Dropdown menu: `DropdownMenu`, `DropdownMenuItem`
  - `ExposedDropdownMenuBox`, `ExposedDropdownMenuBoxScope.ExposedDropdownMenuBoxTextField`, `ExposedDropdownMenuBoxScope.ExposedDropdownMenu`, `ExposedDropdownMenuBoxWithTextField`
- Floating action button: `FloatingActionButton`, `SmallFloatingActionButton`, `LargeFloatingActionButton`,
  `ExtendedFloatingActionButton`
- Icon button: `IconButton`, `IconToggleButton`, `FilledIconButton`, `FilledIconToggleButton`, `FilledTonalIconButton`,
  `FilledTonalIconToggleButton`, `OutlinedIconButton`, `OutlinedIconToggleButton`
- Navigation bar: `NavigationBar`, `NavigationBarItem`
- Radio button: `RadioButtonRow`, `radioGroup`
- Segmented button: `SingleChoiceSegmentedButtonRowScope.SegmentedButton`,
  `MultiChoiceSegmentedButtonRowScope.SegmentedButton`
- Select: `FilledSelect`, `OutlinedSelect`, `SelectOption`
- Tab: `PrimaryTab`, `SecondaryTab`
- Text: `MaterialText`, `TaglessText`
- Text field: `TextField`, `OutlinedTextField`
- Top app bar: `TopAppBar`, `CenterAlignedTopAppBar`, `MediumTopAppBar`, `LargeTopAppBar`

##### `lazy.ext` components

- `List`/`LazyColumnList` (slightly visually inconsistent)

#### About `ext` components (components in the `ext` packages)

The components in the `ext` packages don't follow the `androidx.compose` APIs exactly, but rather provide wrappers that are more idiomatic and conventional on both kinds of targets, wrapping different APIs that can't be unified following the `androidx.compose` APIs.

#### About parameter names

The parameter names with suffixes such as "JsDom" or "ComposeUi" are platform-specific, and only apply on their respective platform(s), Compose HTML / JS DOM or Compose UI platforms.

#### Material Icons

There are two icon modules:

- **`material-icons-core`**: Contains the core set of Material Icons (same icons as in `org.jetbrains.compose.material:material-icons-core`). Only `Filled` and `AutoMirrored.Filled` styles are supported.
- **`material-icons-extended`**: Contains all common Material Icons that exist in both `org.jetbrains.compose.material:material-icons-extended` (v1.7.3) and [Material Symbols](https://github.com/marella/material-symbols). Only `Filled` and `AutoMirrored.Filled` styles are supported. Depends on `material-icons-core`.

Note: The version of `org.jetbrains.compose.material:material-icons-extended` is pinned at 1.7.3 because [Compose officially recommends](https://developer.android.com/develop/ui/compose/graphics/images/material) using individual SVG/vector assets from [Google Fonts](https://fonts.google.com/icons) rather than adding the extended icon library as a dependency, as the latter significantly increases build time and artifact size. As such, these icon modules serve more for **prototyping purposes** currently. When using `material-icons-extended`, ensure that Proguard / R8 resource shrinking is enabled in production builds.

##### Material Symbols & Icons on JS

See [the corresponding section in Compose HTML Material](https://github.com/huanshankeji/compose-html-material?tab=readme-ov-file#material-symbols--icons) for configuring Material Icons on JS.

### Modifiers

- `alpha`
- size modifiers
  - `size`, `sizeIn`, `fillMaxSize`
  - `width`, `widthIn`, `fillMaxWidth`
  - `height`, `heightIn`, `fillMaxHeight`
- `padding`
- `background`
- `border` (visually inconsistent)
- `onClick`
- `verticalScroll`, `horizontalScroll` (`ScrollState` not supported on JS yet)

#### `ext` modifiers

- `outerBorder`
- `roundedCornerBackgroundAndOuterBorder`
- `outerPadding`, `innerPadding`

### Other APIs

- `Alignment`
- `Arrangement`
- `KeyboardOptions`
- `KeyboardActions`
- `PaddingValues`

### ViewModel

The ViewModel module currently supports a subset of the Compose ViewModel APIs. For ViewModel to work properly on Compose HTML / JS DOM, call `com.huanshankeji.compose.ui.window.renderComposableInBodyWithViewModelStoreOwner` instead of `org.jetbrains.compose.web.renderComposableInBody` on JS. These APIs are experimental now.

### Navigation

The navigation module currently supports a small subset of the Compose Navigation APIs, which does not support transition or animation on Compose HTML / JS DOM. These APIs are also experimental now.

## Add to your dependencies

Maven coordinate:

```kotlin
"com.huanshankeji:compose-multiplatform-html-unified-$module:$version"
```

More specifically:

```kotlin
"com.huanshankeji:compose-multiplatform-html-unified-common:$version"
"com.huanshankeji:compose-multiplatform-html-unified-material-icons-core:$version"
"com.huanshankeji:compose-multiplatform-html-unified-material2:$version"
"com.huanshankeji:compose-multiplatform-html-unified-material3:$version"
```

For example, to depend on the Material 3 module with Gradle:

```kotlin
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // ...
                implementation("com.huanshankeji:compose-multiplatform-html-unified-material3:$version")
            }
        }
    }
}
```

View [all the artifacts on Maven Central](https://search.maven.org/search?q=g:com.huanshankeji%20AND%20a:compose-multiplatform-*).

## About Kobweb Silk

The Kotlin/JS (Compose HTML) portion of this project depends on [Kobweb Compose](https://github.com/varabyte/kobweb/blob/main/frontend/kobweb-compose/README.md) of [Kobweb Silk](https://github.com/varabyte/kobweb?tab=readme-ov-file#silk) which is a UI layer built upon Compose HTML that provides `Modifier` (type-safe CSS API wrappers) and layout APIs. Here is a list of topics in their README.md that should be helpful when you use this library in Compose HTML, especially if you need to customize the components further on Kotlin/JS (Compose HTML):

1. [Silk](https://github.com/varabyte/kobweb?tab=readme-ov-file#silk)
   1. [Modifier](https://github.com/varabyte/kobweb?tab=readme-ov-file#modifier)
      1. [attrsModifier and styleModifier](https://github.com/varabyte/kobweb?tab=readme-ov-file#attrsmodifier-and-stylemodifier)
1. [General purpose improvements on top of Compose HTML and Kotlin/JS](https://github.com/varabyte/kobweb?tab=readme-ov-file#general-purpose-improvements-on-top-of-compose-html-and-kotlinjs)
1. [What about Compose Multiplatform for Web?](https://github.com/varabyte/kobweb?tab=readme-ov-file#what-about-compose-multiplatform-for-web)
