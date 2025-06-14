# Nucleus

This repository contains a minimal Android game prototype written in Kotlin using Jetpack Compose. The game presents an atom-like player that can move around the screen and collect free electrons. Each time an electron is collected, the player's "power" increases. This is just a starting point for a more complete game.

## Building

To build the project, you need Android Studio or the Android command-line tools. After installing the required SDK packages, run:

```bash
gradle assembleDebug
```

This will compile the application. You can then install the generated APK on an emulator or device.

If you encounter warnings about AndroidX, ensure that the project contains a `gradle.properties` file with the following lines:

```properties
android.useAndroidX=true
android.enableJetifier=true
```

