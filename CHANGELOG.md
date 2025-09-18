# Changelog

All notable changes to PiTrackerCommons will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [v0.0.4, v0.0.5, v0.0.6] - 2025-09-18

### Added

- Support for new OS (BBK Group)
    - ColorOS
    - FunTouchOS
    - RealmeUI
    - OxygenOS

### Changed

- Revised device and OS detection logic.

### Removed

- OnePlusOS: Same as OxygenOS

## [v0.0.3] - 2025-08-19

### Added

- [DeviceOS](devicedetect/src/main/java/com/ragibn5/devicedetect/DeviceOS.kt)
  An enum type representing the operating system of the device (limited support, see README).

- ## [v0.0.1] - 2025-08-15

### Added

- [DeviceVendor](devicedetect/src/main/java/com/ragibn5/devicedetect/DeviceVendor.kt)
  A value type containing the brand and manufacturer info.
- [DeviceBrand](devicedetect/src/main/java/com/ragibn5/devicedetect/DeviceBrand.kt)
  An enum type representing the brand of the device.
- [DeviceManufacturer](devicedetect/src/main/java/com/ragibn5/devicedetect/DeviceManufacturer.kt)
  An enum type representing the manufacturer of the device.

  Please see the [README](README.md) file for more details.
