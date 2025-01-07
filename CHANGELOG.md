# Change log

## v0.5.0 / 2024-12-05

Important changes:

* This library is renamed from **Compose Multiplatform Material** to **Compose Multiplatform HTML Unified** which better describes its purpose since this release. The **Maven coordinates** have changed accordingly. Please adapt to these changes following the updated README.md.
* replace the terms "Androidx"/"androidx"/"AndroidxCommon"/"androidxCommon" with Compose UI / "ComposeUi" / "composeUi" at appropriate places, both in docs and in code
  
  This introduces some breaking changes. Please adapt accordingly. We are sorry for the inconvenience.

Functional change:

* delegate `Spacer` just to `DivBox` on JS DOM because the Kobweb `Spacer` has a different semantic from the Compose UI one

Miscellaneous changes:

* bump Kotlin to 2.1.0, Compose Multiplatform to 1.7.1, and Kobweb to 0.20.0
* add a "side by side" demo hosted at <https://huanshankeji.github.io/compose-multiplatform-html-unified/>
* add CODE_OF_CONDUCT.md and CONTRIBUTING.md
* use the Kotlin binary compatibility validator
* run IntelliJ IDEA Code Cleanup and Reformat Code for the whole project

## v0.4.0 / 2024-10-24

* fix a bug that a dependent project might crash on Android and add the Android target explicitly for all modules
* no longer publish the legacy module
* bump Kotlin to 2.0.10, Compose Multiplatform to 1.7.0, Kobweb to 0.19.2, and our Compose HTML Material to 0.4.0

### Common

* add `Arrangement.spacedBy`
* Move `LoadingState` here from [Compose HTML Material](https://github.com/huanshankeji/compose-html-material)
* add color parameters to the text composables
* rename `InlineText` to `TaglessText`
* add the `alpha` modifiers
* add a `hidden` modifier
* add the `clickable` modifier and replace `onClick` with it
* add the `PaddingValues` type
* add a `BoxWithConstraints` layout composable that's still buggy on JS DOM
* add `flex-basis: 0` to the weight modifiers on JS DOM to make them consistent with the `androidx.compose` behavior
* split the `padding` modifiers into `outerPadding` and `innerPadding`
* add the `VerticalScrollBox` and `HorizontalScrollBox` composables as (better) alternatives to the `*Scroll` modifiers

### Material 2

* revamp `TopAppBarScaffold` to take a bottom bar, a floating action bottom, and a snackbar host, and fix some of its display issues on JS DOM
  * fix bugs that the action buttons don't show and their `onClick` callbacks are not passed on JS DOM
* add the `SnackbarHost` (the Material 3 snackbar is not available in Material Web yet)
* add `RadioGroupColumn` and improve `RadioRow` on JS DOM
* add a platform-specific implementation for `com.huanshankeji.compose.material2.ext.IconButton` on JS DOM that's more idiomatic, and fix a bug that in some scenarios icons are not shown, by always importing "material-icons/iconfont/material-icons.css"

### Material 3

* add the menu composables `DropdownMenu`, `DropdownMenuItem`, `ExposedDropdownMenuBox`, and `ExposedDropdownMenuWithTextField`
* add the progress indicator composables `LinearProgressIndicator` and `CircularProgressIndicator`
* fix a bug in the text fields on JS DOM that causes the caret to be reset to the start whenever the value changes if the `type` attribute is set
* make multiline text fields work on JS DOM
* add an `isInteractiveJsDom` parameter to ListItemComponents

### Navigation

* initially support navigation

### ViewModel

* initially support ViewModel which delegates to raw UI state on Compose HTML / JS DOM

### Demo

* make the demo UI friendly on mobile

## v0.3.0 / 2024-5-10

Support Material 3. See the Updated README.md for more details.

## v0.2.0 / 2024-4-17

The project now depends on Kobweb Silk on Kotlin/JS (Compose HTML) and there is a universal multiplatform interface for `Modifier`, scopes, etc. akin to those in `androidx.compose`. Obsolete code including `ModifierOrAttrsScope` is moved to a legacy module.
