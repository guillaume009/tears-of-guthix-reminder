Tears of Guthix Reminder RuneLite plugin

This plugin watches chat for the game message "You are eligible to drink from the Tears of Guthix" and shows an infobox reminder. The reminder state is stored in the user's home directory under `~/.runelite/tears_reminder_state.txt` so it persists across logins.

Files added:
- src/main/java/com/krytlex/tearsreminder/TearsReminderPlugin.java
- src/main/java/com/krytlex/tearsreminder/TearsReminderInfobox.java
- src/main/java/com/krytlex/tearsreminder/TearsStateStore.java
- src/main/resources/runelite-plugin.properties

Installation:
- Copy the folder into your RuneLite plugins build environment or include sources in your client build.
- Build/run as you normally would for RuneLite plugins.

Notes:
- The plugin uses a simple text file for persistence. You can replace it with RuneLite's `ConfigManager` if desired.
- The Infobox uses a placeholder icon; replace with an asset if you want a nicer icon.
 - The plugin now supports RuneLite's `ConfigManager` for persistence when run as a proper RuneLite plugin; it will fall back to a text file at `~/.runelite/tears_reminder_state.txt` when `ConfigManager` is not available.
 - The Infobox draws a generated icon; replace or add a PNG in `src/main/resources` for a custom look.

Package and ownership
- The package and provider have been updated to `com.guillaume009` and `guillaume009` respectively.

Building & Integrating with RuneLite

- Option A — Publish locally (recommended if you maintain a separate RuneLite workspace):

	1. Set `runeliteVersion` in `build.gradle` to the RuneLite artifact version your client uses.
	2. Publish the plugin to your local Maven repository:

```bash
./gradlew publishToMavenLocal
```

	3. In your RuneLite workspace `build.gradle`/module where you need the plugin, add:

```groovy
compileOnly 'com.guillaume009:tears-reminder-plugin:1.0-SNAPSHOT'
```

- Option B — Include the project directly in your RuneLite multi-project workspace:

	1. In the RuneLite root `settings.gradle`, add:

```groovy
include ':tears-reminder-plugin'
project(':tears-reminder-plugin').projectDir = file('C:/Users/Krytlex/Downloads/Code projects/tears-reminder-plugin')
```

	2. In the RuneLite module that needs to compile the plugin (for example `runelite-client`), add a project dependency:

```groovy
compileOnly project(':tears-reminder-plugin')
```

General notes
- If you add this as a module to an existing RuneLite workspace, you may not need to set `runeliteVersion` in this project's `build.gradle`.
- The `runelite-plugin.properties` in `src/main/resources` is used by RuneLite to discover the plugin, keep it in place when integrating.

Resolving `net.runelite` imports in your IDE

- Make sure `runeliteVersion` is set in `gradle.properties` (created at project root). Example:

```properties
runeliteVersion=1.8.14
```

- In IntelliJ IDEA: Open the project by selecting the `build.gradle` file or use `File → New → Project from Existing Sources...` and choose the Gradle build. Wait for Gradle import to finish so the `net.runelite` dependencies are fetched.

- Alternatively generate IDE files via Gradle and re-open the project:

```bash
./gradlew --refresh-dependencies idea
```

- If you prefer to work inside the RuneLite multi-module workspace, include this project in the root `settings.gradle` (see earlier section). The workspace will provide the Runelite classes and imports will resolve automatically.
