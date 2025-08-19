# devicedetect

A lightweight library for retrieving type-safe device and manufacturer-specific information on
Android.

## Prerequisites

- **Android Studio**: Ladybug (2024.2.2) or later
- **AGP**: 8.8.2 or later
- **Java runtime (Along with Gradle JDK)**: JDK 17 or later

## Installation & Setup

### 1. Clone the Repository

Using HTTPS:

```bash
git clone https://github.com/Ragibn5/devicedetect.git
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select **File** → **Open** and navigate to the cloned directory

### 3. Sync Project

Android Studio will automatically prompt to sync the project.
If not, click **File** → **Sync Project with Gradle Files**.
Make sure you have met the prerequisites described above, otherwise the sync may not be successful.

### Usage

```kotlin
val vendor = DeviceVendor.detect()
findViewById<TextView>(R.id.preview).setText(
    String.format(
        "Brand: %s\nManufacturer: %s",
        vendor.brand,
        vendor.manufacturer,
    )
)
```

Available info (as of now):

- [DeviceVendor](devicedetect/src/main/java/com/ragibn5/devicedetect/DeviceVendor.kt)
  A value type containing the brand and manufacturer info.
- [DeviceBrand](devicedetect/src/main/java/com/ragibn5/devicedetect/DeviceBrand.kt)
  An enum type representing the brand of the device.
- [DeviceManufacturer](devicedetect/src/main/java/com/ragibn5/devicedetect/DeviceManufacturer.kt)
  An enum type representing the manufacturer of the device.
- [DeviceOS](devicedetect/src/main/java/com/ragibn5/devicedetect/DeviceOS.kt)
  An enum type representing the operating system of the device.

Note, The OS detection is verified for only Xiaomi, Transsion, and Nothing devices as of now.
We will expand the library to support more operating systems with upcoming releases.

## Building a New Local Release

Currently, we use local maven publishing method to create a release and use it as a library (in
other projects).

To build and publish a new release version of the library (to your local Maven repository), run:

```bash
./gradlew clean assembleRelease publishToMavenLocal
```

**Note:**
Make sure you change the version inside the `publishing` block of the library's
[`build.gradle`](devicedetect/build.gradle.kts) file before creating a new local release.
Running the above command while not changing the version will overwrite the previous release build
files.

## Using in Other Projects

If you are managing the dependency repositories with settings.gradle (newer projects):
Add `maven { url 'https://jitpack.io' }` at the end of `dependencyResolutionManagement.repositories`
block:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Or, if you are managing the dependency repositories with build.gradle (older projects):
Add `maven { url 'https://jitpack.io' }` at the end of `rootProject.allprojects.repositories` block:

```groovy
rootProject.allprojects {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Add this to your module build.gradle:

```groovy
implementation 'com.github.Ragibn5:devicedetect:0.0.3'
```

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit changes: `git commit -am 'Add feature'`
4. Push to branch: `git push origin feature-name`
5. Submit a Pull Request

## License

Click [here](LICENSE) to see the license.