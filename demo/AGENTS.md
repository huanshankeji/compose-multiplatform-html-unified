# Demo Module — Agent Instructions

## Visual Validation via Browser Demos

AI agents with browser automation (e.g., Playwright) can visually validate **both** JS DOM and Wasm JS (Compose UI) rendering:

1. **JS DOM** (HTML/CSS rendering): `./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun` — serves at `http://localhost:8080`
2. **Wasm JS** (Compose UI canvas rendering): `./gradlew :compose-multiplatform-html-unified-demo:wasmJsBrowserDevelopmentRun` — serves at `http://localhost:8080`

Both targets can run simultaneously — when port 8080 is taken, Gradle assigns an alternative (e.g., 8081). Check task output for the actual port.

### Wasm JS / Shadow DOM Access

For the Wasm JS target, UI elements are rendered on a canvas inside a shadow DOM. To interact via Playwright, access accessibility nodes through `document.body.shadowRoot` and query `[role="button"]` etc.

### Iterative Validation Loop

Make code changes → rebuild → refresh browser → verify visual output. Use **both targets** to compare visual consistency between JS DOM and Compose UI rendering.

**Note**: The JVM desktop demo (`./gradlew :compose-multiplatform-html-unified-demo:run`) requires a GUI display and cannot be run by headless AI agents. Use the Wasm JS target for Compose UI visual validation instead.
