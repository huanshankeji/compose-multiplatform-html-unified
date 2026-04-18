---
name: kotlin-debugging-unresolved-reference-file-clash
description: >
  Diagnoses and fixes Kotlin JVM "Unresolved reference" compilation errors
  caused by file facade class name clashes. Use when you encounter "Unresolved
  reference" or "can't resolve" errors in a Kotlin JVM or Kotlin Multiplatform
  project where dependencies appear correct, especially after adding, moving,
  or renaming Kotlin files with top-level declarations.
license: Apache-2.0
metadata:
  author: huanshankeji
  version: "1.0.0"
---

# Debugging "Unresolved reference" errors caused by Kotlin file facade clashes

## Background: Kotlin file facades on JVM

When a Kotlin file contains top-level declarations (functions, properties, or
type aliases), the Kotlin/JVM compiler generates a **file facade** class in the
output bytecode. The facade class is named `<FileName>Kt` (e.g., `Utils.kt`
produces `UtilsKt.class`) and is placed in the package declared in the file.

If two Kotlin files with the **same file name** and the **same package** end up
on the same compilation classpath — even if they reside in different source
directories, source sets, or modules — their generated file facade classes
**clash**. Instead of a clear "duplicate class" message, the Kotlin compiler
often reports **misleading "Unresolved reference"** errors on symbols that are
actually defined correctly. This makes the root cause difficult to identify.

## When to use

Apply this debugging procedure when **all** of the following are true:

1. The compiler reports `Unresolved reference: <symbol>` or similar "can't
   resolve" errors.
2. The referenced symbol **does** exist in the source code with correct
   visibility and is in the expected dependency.
3. Project dependencies and imports **look correct** — no obvious missing
   dependency, typo, or visibility issue.

These errors frequently occur after:

- Adding a new Kotlin file that happens to share a name and package with an
  existing file in another source set or module.
- Moving or renaming files across source sets (e.g., `commonMain` →
  `jvmMain` in Kotlin Multiplatform) without cleaning up the old location.
- Refactoring that introduces files with common names (e.g., `Icons.kt`,
  `Utils.kt`, `Extensions.kt`) in the same package across modules.

## Diagnostic steps

### Step 1: Search for duplicate file names in the same package

Search the project for Kotlin source files (`.kt`) that share the **same file
name** and have the **same `package` declaration**. Pay special attention to
files across different source roots or source sets that are compiled together
for the same target (e.g., `src/main/kotlin` and another source directory both
feeding into a JVM compilation).

```bash
# Example: find all files named "Icons.kt" in the project
find . -name "Icons.kt" -type f
```

Then compare the `package` declarations in each result. If two or more files
share the same file name and the same package, you have found the clash.

### Step 2: Verify the clash is the root cause

Temporarily rename one of the clashing files (e.g., `Icons.kt` →
`Icons2.kt`) and rebuild. If the "Unresolved reference" errors disappear, the
file facade clash was the root cause.

### Step 3: Apply a fix

Choose one of the following fixes:

#### Option A: Rename the file (recommended)

Rename one of the clashing files to a unique name. This is the simplest and
most reliable fix.

```
# Before (clash)
moduleA/src/main/kotlin/com/example/Icons.kt
moduleB/src/main/kotlin/com/example/Icons.kt

# After (fixed)
moduleA/src/main/kotlin/com/example/Icons.kt
moduleB/src/main/kotlin/com/example/PlatformIcons.kt
```

#### Option B: Change the package

Move one of the files to a different package so the fully qualified facade
class names differ.

#### Option C: Use `@file:JvmName` (advanced)

If renaming the file is undesirable, annotate one of the files with
`@file:JvmName` to give its facade class a different JVM name:

```kotlin
@file:JvmName("PlatformIcons")

package com.example

// top-level declarations...
```

This changes the generated class from `IconsKt.class` to
`PlatformIcons.class`, resolving the clash. Note that this only affects the
JVM facade name; Kotlin callers are unaffected.

### Step 4: Clean and rebuild

After applying the fix, perform a clean build to ensure no stale class files
remain:

```bash
./gradlew clean build
```

## Common scenarios

### Kotlin Multiplatform projects

In Kotlin Multiplatform projects, `commonMain`, `jvmMain`, and other source
sets are compiled together for JVM targets. A file `Utils.kt` with package
`com.example` in `commonMain` and another `Utils.kt` with the same package in
`jvmMain` will clash. A conventional solution here is to rename the
platform-specific file with a platform suffix, e.g., `Utils.jvm.kt`.

### Multi-module Gradle projects

When module A depends on module B and both contain a file with the same name
and package containing top-level declarations, the file facades collide on the
classpath.

### After adding or moving files

AI coding agents and developers frequently trigger this when adding new files
with common names (e.g., `Icons.kt`, `Extensions.kt`, `Helpers.kt`) without
checking whether a same-named file already exists in the same package elsewhere
in the project.

## Guardrails

- Do **not** assume the error is a missing dependency or import if the symbol
  clearly exists in the source.
- Do **not** add redundant dependencies or imports to try to fix the error
  without first checking for file clashes.
- Before creating a new Kotlin file with top-level declarations, search the
  project for existing files with the same name in the same package.

## References

- [Kotlin documentation: Packages and imports](https://kotlinlang.org/docs/packages.html)
- [Kotlin documentation: Java interop — Package-level functions](https://kotlinlang.org/docs/java-to-kotlin-interop.html#package-level-functions)
- [KT-83413: Misleading "Unresolved reference" errors caused by file facade class name clashes](https://youtrack.jetbrains.com/issue/KT-83413)
