# Comparison: PR #144 vs Commit d2f2e96

Both changes reorganize `.github/copilot-instructions.md` into AGENTS.md files, making the instructions
universal for all AI agents rather than just GitHub Copilot. This document compares them point by point.

## Overview of Each Change

| | PR #144 | Commit d2f2e96 |
|---|---|---|
| **Title** | "Reorganize `.github/copilot-instructions.md` into `AGENTS.md` and `material3/AGENTS.md`" | "Simplify, transfer/reorganize `.github/copilot-instructions.md` into AGENTS.md and other more appropriate agent context files with Claude Code" |
| **Author** | GitHub Copilot agent | ShreckYe (human, using Claude Code) |
| **Status** | Open draft PR | Direct commit to a branch (not in main) |
| **Files changed** | 3: delete `.github/copilot-instructions.md`, rewrite `AGENTS.md`, add `material3/AGENTS.md` | 3: delete `.github/copilot-instructions.md`, rewrite `AGENTS.md`, add `demo/AGENTS.md` |
| **Net lines** | +282 / -407 | +162 / -407 |

---

## Point-by-Point Comparison

### 1. Path-Scoped File for Material 3 Lookup

**PR #144**: Creates `material3/AGENTS.md` with a 7-line file containing the m3.material.io lookup
guidance and a pointer to README.md.

- **Pro**: Correctly path-scopes the M3 lookup guidance to the `material3/` subtree, so agents working
  in that directory receive relevant context automatically.
- **Pro**: Clean separation of concerns — a contributor working only on `material3/` sees only what's
  relevant.
- **Con**: Very thin file (7 lines); the full lookup context (e.g., "links to both Jetpack Compose and
  Material Web implementations") is present but brief.

**Commit d2f2e96**: No separate `material3/AGENTS.md`. The M3 lookup guidance is merged into the "Adding
New Components" section of root `AGENTS.md`.

- **Con**: Not path-scoped; agents working in `material3/` do not get targeted context from a
  directory-level AGENTS.md.
- **Pro**: Fewer files; the guidance is still present in the root for all agents.

---

### 2. Path-Scoped File for Visual Validation (demo module)

**PR #144**: Visual validation details are kept in the root `AGENTS.md` under the "Build and Test"
section.

- **Con**: Not path-scoped to the `demo/` directory; agents working in `demo/` don't get a dedicated
  context file.

**Commit d2f2e96**: Extracts visual validation details into `demo/AGENTS.md` (20 lines).

- **Pro**: Correctly path-scopes the browser automation / Playwright guidance to the `demo/` subtree.
- **Pro**: The root `AGENTS.md` is less cluttered by low-level browser automation notes.
- **Pro**: Mirrors the same path-scoping principle that PR #144 uses for `material3/`, but applied to the
  demo module instead.

---

### 3. Overall Verbosity and Tone

**PR #144**: More verbose, closer in style to the original `copilot-instructions.md`. Retains extensive
explanations and rationale for each convention.

- **Pro**: More instructive for human readers or less-capable AI agents that benefit from detailed
  context.
- **Con**: ~282 added lines vs. 162 in the commit — larger maintenance surface.
- **Con**: Some sections duplicate phrasing directly from the original without simplification.

**Commit d2f2e96**: Significantly more concise. Uses compact prose, shorter bullet lists, and a
table for the module structure.

- **Pro**: Easier for AI agents to parse and act on; less noise.
- **Pro**: Lower maintenance burden going forward.
- **Con**: Some detail is lost (e.g., URL format for finding Compose UI source, full CSS shortcuts
  examples, full `apiDump` blockquote rationale).

---

### 4. Module Structure Representation

**PR #144**: Uses a directory-tree format (same as the original):

```
common/               — Core APIs, layouts, modifiers
material3/            — Material Design 3 components
...
```

**Commit d2f2e96**: Uses a Markdown table:

| Module | Purpose |
|--------|---------|
| `common` | Core APIs, layouts, modifiers |
| `material3` | Material Design 3 components |

- **Commit pro**: Tables are easier for AI agents to parse; also notes `material2` is broken/unmaintained
  and `material-icons` uses the new `core`/`extended` split (which is more up-to-date).
- **PR con**: Retains the `material-icons-core` name (old), not reflecting the updated split.

---

### 5. Build Commands — API Check Failure Instructions

**PR #144**: Includes the full `apiCheck` failure blockquote and three validation commands, but is
**missing `wasmJsBrowserDevelopmentRun`**:

```bash
./gradlew publishToMavenLocal
./gradlew :compose-multiplatform-html-unified-demo:run
./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun  # for JS browser
```

- **Con**: Omits the `wasmJsBrowserDevelopmentRun` validation command that is important for Compose UI
  rendering verification.

**Commit d2f2e96**: Compact single-sentence policy + all validation commands are listed in the "Demo
Validation" block (including `wasmJsBrowserDevelopmentRun`). The failure note refers agents to use
`publishToMavenLocal` and demo runs.

- **Pro**: `wasmJsBrowserDevelopmentRun` is present in the demo validation list.
- **Con**: The specific "if `apiCheck` fails, do these" commands are not repeated in a dedicated block,
  so agents must infer they should use the demo validation commands.

---

### 6. Demo Validation Commands

**PR #144**: Lists all four demo commands under "Demo Validation" including `sideBySideBrowserDistribution`.

**Commit d2f2e96**: Lists all four demo commands as well.

Both are equivalent here. ✓

---

### 7. Troubleshooting / Build Issues

**PR #144**: Has a "Troubleshooting" section with four bullets (Network, Memory/OOM, kotlin-js-store,
Gradle daemon timeout). Explicitly notes `--no-daemon` flag.

**Commit d2f2e96**: Has the same four bullets, slightly more compact. The `kotlin-js-store` note is
placed prominently as a standalone "Note" in the Build Commands section.

Both are equivalent in content; the commit surfaces the `kotlin-js-store` note more prominently. ✓

---

### 8. Snapshot Dependencies Note

**PR #144**: Placed in a "Prerequisites" section with an **IMPORTANT** callout at the top of the Build
section.

**Commit d2f2e96**: A short plain sentence at the end of the Build Commands section.

- **PR pro**: More visible; agents are less likely to miss it.
- **Commit con**: Easy to overlook since it appears after the main commands.

---

### 9. Code Style — Trailing Commas

**PR #144**: Full bullet list with all exceptions and a code example showing correct vs. incorrect
placement with inline comments.

**Commit d2f2e96**: Single-sentence summary ("Add to the last parameter of multi-line Composable
parameter/argument lists. Do NOT add for single-line lists, single parameters, or annotations.").

- **PR pro**: All edge cases are explicit (e.g., annotation exception, end-of-line comment convention).
- **Commit con**: The end-of-line comment placement rule is not mentioned at all.

---

### 10. Code Style — Nullable Composable Types

**PR #144**: Multi-bullet explanation with the "do not use" counterexample and the extension function
receiver exception.

**Commit d2f2e96**: Single line: `Use @Composable (() -> Unit)?`, no mention of the extension receiver
exception.

- **PR pro**: Complete — includes the important `fun (@Composable …).foo()` exception.
- **Commit con**: The extension function receiver exception is missing.

---

### 11. Expect/Actual Patterns

**PR #144**: Three detailed bullets for `expect interface`, `expect enum class`, and `expect class`, with
concrete type examples (`SnackbarVisuals`, `SnackbarHostState`).

**Commit d2f2e96**: Same three patterns but compressed to 3 bullet lines with no specific type examples.

- **PR pro**: Easier to understand from the concrete examples.
- **Commit pro**: More scannable for an agent that only needs to know the rule.

---

### 12. Component Organization (main vs. ext package)

**PR #144**: Two numbered items with sub-bullets and examples; labs annotation as a third item.

**Commit d2f2e96**: Two bullets (main/ext), then labs deps mentioned in "Adding New Components" step 5.

- **PR pro**: Labs annotations clearly documented as a third organization principle.
- **Commit con**: Labs annotation treatment is present but spread across two sections.

---

### 13. Unsupported Compose UI APIs (commented-out lines pattern)

**PR #144**: Full KDoc example with two `@param` bullets and the complete `expect fun Text(...)` showing
two commented-out parameters.

**Commit d2f2e96**: Shorter example with one `@param` bullet and one commented-out parameter.

- **PR pro**: The two-parameter example shows the pattern more clearly as a list.
- Both are functionally equivalent. The detail difference is minor.

---

### 14. Platform-Limited Parameters

**PR #144**: Dedicated subsection with two bullets and two concrete examples (`enabled` in tabs, `space`
in segmented button rows).

**Commit d2f2e96**: Same rule as a short paragraph; no concrete examples.

- **PR pro**: Concrete examples make the rule immediately actionable.

---

### 15. "Copied and Adapted" Code

**PR #144**: Includes the full URL pattern for finding Compose Multiplatform Core source:
```
https://raw.githubusercontent.com/JetBrains/compose-multiplatform-core/v<VERSION>/...
```
Also includes the three "ensure" checklist items and the exception note.

**Commit d2f2e96**: Shorter. Only gives the comment format, omits the URL pattern for finding source
and the three checklist items.

- **PR pro**: The URL pattern is highly useful when agents need to actually find the original source.
- **PR pro**: The three checklist items (names/types, definition order, unsupported APIs documented) are
  a useful quality gate.
- **Commit con**: Agents may not know how to find the upstream Compose UI source.

---

### 16. CSS Style Shortcuts

**PR #144**: Dedicated "CSS Style Shortcuts" subsection with two `overflow()`/`minHeight()` examples
and a note about upstream contribution.

**Commit d2f2e96**: One-line bullet in "JS DOM Notes": "Prefer type-safe CSS shortcuts over raw
`property()` calls".

- **PR pro**: The concrete examples (`overflow(Overflow.Hidden)` vs `property("overflow", "hidden")`)
  make the rule instantly clear.
- **Commit con**: No examples; agents may not know which shortcuts exist or how to use them.

---

### 17. `fillMax*Stretch` vs `fillMax*`

**PR #144**: Full dedicated subsection with the full explanation of why `100%` causes overflow and when
to use each variant.

**Commit d2f2e96**: One-line bullet in "JS DOM Notes": "Prefer `fillMaxWidthStretch()` over
`fillMaxWidth()` — avoids overflow from padding/margin (uses CSS `stretch` instead of `100%`)".

- **PR pro**: More complete explanation; agents understand when NOT to use the stretch variants (when
  `fraction` parameter is needed).
- **Commit con**: The `fraction` parameter exception is not mentioned.

---

### 18. Avoiding Duplicate Components

**PR #144**: Dedicated "Avoiding Duplicate Components" subsection with a concrete `ListScope.ListItem`
example.

**Commit d2f2e96**: One-line bullet in "JS DOM Notes": "Search existing codebase before adding components
to avoid duplicates".

- **PR pro**: The concrete example is a useful reminder.

---

### 19. Adding New Components — Step List

**PR #144**: Four steps:
1. Add to demo (`Material3.kt`)
2. Visual consistency comparison (side-by-side demo)
3. Note on inherent platform differences
4. README update

**Commit d2f2e96**: Five steps:
1. **Implement** in `commonMain`, `composeUiMain`, `jsMain`
2. Add to demo
3. Compare rendering via side-by-side demo (links to `demo/AGENTS.md` for browser automation details)
4. Update README.md API catalog
5. Mark JS labs dependencies with `@MaterialWebLabsApi`

- **Commit pro**: Step 1 (implement) is explicit — the PR assumes agents know to implement before adding
  to demo.
- **Commit pro**: Step 5 (labs deps) is explicit here; in PR it's in the component organization section.
- **PR pro**: Step 3 (inherent platform differences note) reminds agents to document them.
- **Commit pro**: Cross-references `demo/AGENTS.md` for browser automation details, demonstrating
  intentional path-scoping design.

---

### 20. Sections Removed vs. Original (both agree)

Both approaches agree on removing the following from the original `copilot-instructions.md` (good
decision in both cases):

- Repository statistics (size, language — already obvious)
- Hardcoded dependency version numbers (AGP 8.12.3, Kotlin 2.3.20, etc.) — become stale quickly
- Build timing estimates (5–10 min, 1–3 min, etc.) — non-actionable
- Root directory file list — low value
- "Trust These Instructions" meta-notes — not actionable for agents
- `./gradlew --version` basic setup check
- `--continue` flag note for platform-specific builds

---

### 21. Section Present in Neither (potential gap)

The following item from the original `copilot-instructions.md` is **not present in either** change:

- **CSS imports for Material Icons** ("Located in `jsMain` source sets — requires CSS imports for Material
  Icons"): Neither approach preserves this note, which could cause agents to miss a required step when
  adding icon support.

---

## Summary Table

| Point | PR #144 | Commit d2f2e96 |
|---|---|---|
| `material3/AGENTS.md` (path-scoped) | ✅ Created | ❌ Not created |
| `demo/AGENTS.md` (path-scoped) | ❌ Not created | ✅ Created |
| Overall verbosity | Verbose (more detail) | Concise (AI-friendly) |
| Module structure representation | Directory tree | Markdown table (more scannable) |
| `wasmJsBrowserDevelopmentRun` in apiCheck instructions | ❌ Missing | ✅ Present |
| Snapshot dependencies visibility | ✅ IMPORTANT callout | ⚠️ Easy to overlook |
| Trailing comma: end-of-line comment rule | ✅ Explicit | ❌ Missing |
| Nullable composable: extension receiver exception | ✅ Present | ❌ Missing |
| Expect/actual: concrete type examples | ✅ Yes | ❌ No |
| Labs annotation as organization principle | ✅ Explicit section | ⚠️ Split across sections |
| Unsupported APIs: full KDoc example | ✅ Two params | ⚠️ One param |
| Platform-limited params: concrete examples | ✅ Yes | ❌ No |
| "Copied and adapted": source URL pattern | ✅ Yes | ❌ Missing |
| CSS shortcuts: concrete examples | ✅ Yes | ❌ No |
| `fillMax*Stretch`: fraction exception | ✅ Explicit | ❌ Missing |
| Avoiding duplicates: concrete example | ✅ Yes | ❌ No |
| Adding components: "implement" step | ❌ Missing | ✅ Explicit |
| Adding components: labs deps step | ⚠️ In org. section | ✅ Explicit step |
| Adding components: inherent differences note | ✅ Step 3 | ❌ Missing |
| CSS imports for Material Icons note | ❌ Missing | ❌ Missing |

---

## Recommendation

The two approaches are complementary. An ideal merged result would:

1. **Adopt the commit's path-scoping strategy for both** `demo/AGENTS.md` AND `material3/AGENTS.md`.
2. **Adopt the commit's conciseness** for the overall AGENTS.md to reduce maintenance burden.
3. **Restore from PR #144**:
   - The end-of-line comment trailing comma rule
   - The extension function receiver exception for nullable composable types
   - The source URL pattern in "Copied and Adapted"
   - The `fraction` parameter exception for `fillMax*Stretch`
   - Concrete CSS shortcut examples (at least one)
4. **Adopt from commit d2f2e96**:
   - The "implement" step (step 1) in Adding New Components
   - The labs deps step in Adding New Components
   - The table representation for module structure
   - The explicit `wasmJsBrowserDevelopmentRun` in apiCheck failure instructions
5. **Add to both** (currently missing from both): CSS imports for Material Icons note.
