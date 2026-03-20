# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.7] - 2025-03-20

### Changed

- Updated opentmf-clients-base to 1.1.7.

## [1.1.6] - 2025-03-11

### Added

- `CHANGELOG.md` at project root (Keep a Changelog format); version history moved out of README.

### Changed

- README now links to `CHANGELOG.md` for version history instead of listing it inline.
- Maven release build: source, javadoc, GPG, and Central publishing plugins moved into a `release` profile (active with `-Prelease` or during `release:perform`).
- Updated opentmf-clients-base to 1.1.6.
- Updated Spring Boot to 3.5.11.
- Updated json-path to 3.0.0.
- Updated Maven plugins: surefire/failsafe 3.5.5, release 3.3.1, Sonar 5.5.0.6356, central-publishing 0.10.0.
- Updated Testcontainers to 1.21.4.
- License URL switched to HTTPS in `pom.xml`.

## [1.1.5]

### Changed

- Updated opentmf-clients-base to 1.1.5
- Updated Spring Boot base to 3.5.8

## [1.1.4]

### Changed

- Updated opentmf-clients-base to 1.1.4

## [1.1.3]

### Changed

- Updated opentmf-clients-base to 1.1.3

## [1.1.2]

### Changed

- Initial open-source version: replaced pia with opentmf branding and dependencies

## [1.1.1] - Backward incompatible

### Changed

- Updated tmf-clients-base to 1.1.0
- Updated Spring Boot to 3.4.4

## [1.1.0]

### Changed

- Updated pia-web-clients to 1.0.9
- Updated tmf-clients-base to 1.0.5
- Updated Spring Boot to 3.4.3

## [1.0.9]

### Changed

- Updated pia-web-clients to 1.0.8 (fewer dependencies for the reactive WebClient)
- Updated Spring Boot to 3.4.1

## [1.0.8]

### Fixed

- Bean name for quoteClientProvider

## [1.0.7]

### Changed

- Updated pia-web-clients to 1.0.7

## [1.0.6]

### Added

- TMF-681 CommunicationsMessageClient

### Changed

- Updated pia-tmf-v4-models to v3

## [1.0.5]

### Added

- `@Getter` on client implementations for simpler usage

### Changed

- Updated tmf-clients-base to 1.0.3
- Updated pia-web-clients to 1.0.6

## [1.0.4]

### Changed

- Updated tmf-clients-base to 1.0.2
- Updated pia-web-clients to 1.0.5

## [1.0.3]

### Changed

- Updated tmf-clients-base to 1.0.1
- Updated pia-web-clients to 1.0.4

## [1.0.2]

### Changed

- Renamed modules by appending `v4`
- Switched to the separate tmf-clients-base project
- Simplified test dependencies

## [1.0.1]

### Added

- GenericClient

## [1.0.0]

- Initial release
