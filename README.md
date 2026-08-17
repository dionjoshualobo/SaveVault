# SaveVault – Instagram Bookmark Organizer

## Overview
SaveVault is a native Android application built with **Kotlin** and **Jetpack Compose**. It lets users save Instagram post URLs via the Android Share intent, fetches post metadata using Instagram OEmbed, stores posts locally with **Room**, and provides tagging, filtering, and search features.

## Prerequisites
- **Android Studio Arctic Fox (2020.3.1) or newer** (includes the latest Android Gradle Plugin).
- **Java Development Kit (JDK) 17** (or the version bundled with Android Studio).
- **Android SDK** with at least API level 26 (Android 8.0) installed.
- **Gradle Wrapper** is included; no separate Gradle installation required.
- Optional: a physical Android device or an emulator with Google Play services.

## Getting the Source
```bash
# Clone the repository (if you haven't already)
git clone <repo‑url>
cd SaveVault
```
The repository root is the `SaveVault` folder (the `.git` directory lives here).

## Building the Project
The Gradle wrapper scripts (`./gradlew` on Linux/macOS or `gradlew.bat` on Windows) handle all build tasks.

### Sync Gradle
Open the project in Android Studio. The IDE will automatically sync the Gradle files. You can also run manually:
```bash
./gradlew --no-daemon clean
./gradlew --no-daemon build
```
### Run the App locally
1. **Start an emulator** or connect a device via USB.
2. From Android Studio press the **Run** button (▶) or use the CLI:
```bash
./gradlew installDebug   # builds and installs the debug APK
adb shell am start -n com.savevault.instabookmark/.MainActivity
```
The app will launch, ready to accept Instagram URLs through the Share menu.

## Testing
Unit tests are written for the ViewModel and Room DAO.
```bash
./gradlew test            # Run JVM unit tests
./gradlew connectedAndroidTest   # Run instrumentation tests on a device/emulator
```

## Project Structure (high‑level)
- `app/src/main/java/com/savevault/instabookmark/` – Kotlin source files.
  - `ui/` – Compose UI (DashboardScreen, PostCard, dialogs, etc.).
  - `data/` – Room entities, DAO, database.
  - `network/` – Retrofit service for Instagram OEmbed.
  - `MainViewModel.kt` – Central ViewModel handling UI state, search, tags, and network calls.
- `app/src/main/res/` – Resources (themes, colors, icons).
- `build.gradle.kts` – App module dependencies (Compose, Room, Retrofit, Coil, etc.).
- `settings.gradle.kts` – Includes the `:app` module.

## Common Commands
| Command | Description |
| ------- | ----------- |
| `./gradlew assembleDebug` | Compile the debug APK without installing. |
| `./gradlew installDebug` | Build and install the app on a connected device/emulator. |
| `./gradlew lint` | Run Android Lint checks. |
| `git status` | Check repository status. |
| `git log --oneline` | View commit history (the repo is intentionally kept local). |

## Notes
- The repository is **local‑only**; there is no remote origin configured. All commits stay on your machine.
- The UI uses a dark Material 3 theme with custom colors and glass‑morphism surfaces for a premium look.
- The app uses the free **Microlink.io** API (`network/MetadataService.kt`) to extract Instagram post metadata (title, author, thumbnail) without needing a Facebook Developer account or Access Token.

---
*Happy coding!*

## Running without Android Studio

You can build, install, and run the app entirely from the command line using the Gradle wrapper and Android SDK command‑line tools.

### 1. Install required tools
- **Java JDK 17** (or the version bundled with Android Studio).
- **Android SDK command‑line tools**. Download from the Android developer site and unzip, e.g.:
```bash
mkdir -p $HOME/Android/sdk && cd $HOME/Android/sdk
curl -O https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip
unzip commandlinetools-linux-9477386_latest.zip
mv cmdline-tools latest
```
- Add the SDK to your environment:
```bash
export ANDROID_SDK_ROOT=$HOME/Android/sdk
export PATH=$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools:$PATH
```
- Use `sdkmanager` to install the platform, build tools, and platform‑tools (required for `adb` and the emulator):
```bash
sdkmanager "platforms;android-33" "build-tools;33.0.2" "platform-tools" "system-images;android-33;google_apis;x86_64"
```

### 2. (Optional) Create an Android Virtual Device (AVD)
If you don’t have a physical device, create an emulator:
```bash
avdmanager create avd -n pixel2 -k "system-images;android-33;google_apis;x86_64" --device "pixel"
# Start the emulator
emulator -avd pixel2 &
```
Wait for the emulator to finish booting (`adb wait-for-device`).

### 3. Build the APK
From the repository root (`~/SaveVault`):
```bash
./gradlew assembleDebug   # generates app-debug.apk under app/build/outputs/apk/debug/
```
The resulting APK will be at:
```
app/build/outputs/apk/debug/app-debug.apk
```

### 4. Install the APK on a device/emulator
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```
`-r` forces reinstall if the app is already installed.

### 5. Launch the app
```bash
adb shell am start -n com.savevault.instabookmark/.MainActivity
```
The app should appear on the device/emulator, ready to accept Instagram URLs via the Android Share menu.

### 6. Useful Gradle commands (CLI only)
| Command | What it does |
| ------- | ------------ |
| `./gradlew clean` | Removes all build outputs. |
| `./gradlew assembleDebug` | Compiles the debug APK. |
| `./gradlew installDebug` | Builds **and** installs the debug APK in one step. |
| `./gradlew lint` | Runs Android Lint checks. |
| `./gradlew test` | Executes JVM unit tests. |
| `./gradlew connectedAndroidTest` | Runs instrumentation tests on a connected device or emulator. |

### 7. troubleshooting
- If `adb` cannot find a device, ensure USB debugging is enabled on a physical device or that the emulator is running.
- For missing SDK components, rerun `sdkmanager` with the required packages.
- Gradle may complain about missing SDK paths; set `ANDROID_SDK_ROOT` as shown above.

Now you can develop, build, and test the SaveVault app without ever opening Android Studio.

