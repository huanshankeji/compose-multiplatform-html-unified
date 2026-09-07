See [Copilot instructions](.github/copilot-instructions.md) for this repository.

Organization-wide standards and open-source library map: [@huanshankeji/.github general agent instructions](https://github.com/huanshankeji/.github/blob/main/docs/general-agent-instructions.md).

## Cursor Cloud specific instructions

The build/test/run commands are documented in [Copilot instructions](.github/copilot-instructions.md); prefer those. Notes below are cloud-VM-specific caveats only.

- **Toolchain**: The project targets **JDK 17** (per CI), not the VM's default JDK. Gradle is pinned to JDK 17 via `org.gradle.java.home` in `~/.gradle/gradle.properties`, and `JAVA_HOME` is set in `~/.bashrc`. Don't rely on `java -version` in a fresh non-login shell; Gradle uses the pinned home.
- **Android SDK**: Installed at `~/android-sdk` (compileSdk 36) and referenced via `sdk.dir` in the git-ignored `local.properties`. It's required for the `demo` module (`com.android.application`) to even configure, so all Gradle tasks (including `check`) depend on it being present.
- **Running the demo (headless-friendly targets)**:
  - JS DOM: `./gradlew :compose-multiplatform-html-unified-demo:jsBrowserDevelopmentRun --continuous` → http://localhost:8080
  - Wasm JS (Compose UI canvas): `./gradlew :compose-multiplatform-html-unified-demo:wasmJsBrowserDevelopmentRun --continuous` → http://localhost:8081 (auto-bumps to next port if 8080 is taken)
- **Wasm JS canvas gotcha**: The Wasm target compiles and serves fine, but the Compose UI canvas often fails to render in the cloud VM's Chrome (blank screen + a swallowed `kotlin.RuntimeException` in `FlushCoroutineDispatcherChecker` during init). Root cause is that WebGL is **software-only** here (`chrome://gpu` shows "WebGL: Software only, hardware acceleration unavailable"); skiko needs a working GL context. Use the **JS DOM** target for visual validation in the cloud VM.
- **JVM desktop demo** (`./gradlew :compose-multiplatform-html-unified-demo:run`) needs a GUI display and cannot run in the headless VM.
