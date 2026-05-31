# KwinB

KwinB is a Kotlin CLI tool for quickly spinning up a new Kotlin CLI project.

The goal of KwinB is to give you a ready-to-build command-line application skeleton so you can skip the repetitive setup work and focus on the actual features you want to create the project for.

It generates a Kotlin/JVM CLI project with Gradle, Clikt, Shadow, and a basic command structure already wired together.

## What KwinB Generates

A generated project includes:

- A Kotlin/JVM Gradle project
- A runnable CLI entry point
- A starter Clikt command
- Gradle Shadow JAR configuration
- Kotlin test setup
- Optional GitHub Actions workflow for building native executables
- Optional Linux `.deb` and macOS `.dmg` packaging workflow

# Usage

```
$ kwinb --help
```

Use KwinB with the `create` command to generate a new Kotlin/JVM CLI project:
```
$ kwinb create <project-name> --output-dir <output-directory>
```
The project name should be in PascalCase. The generated project folder will be placed in the specified output directory.
Basically whatever project you create with KwinB will have the same project structure and usage as KwinB itself.

Use KwinB `add` to add an optional workflows, libraries, etc post project creation:
```
$ kwinb add <feature-name> <project-folder>
```

## Libraries and Tools Used

KwinB uses the following libraries and tools:

### Kotlin JVM

KwinB generates Kotlin/JVM command-line applications.

- Kotlin version: `2.3.21`
- JVM toolchain: Java `21`

### Pebble
KwinB uses [Pebble](https://pebbletemplates.io/) for templating.

### Clikt

KwinB uses [Clikt](https://ajalt.github.io/clikt/) to build command-line interfaces.

Clikt provides:

- Command and subcommand support
- Arguments and options
- Help text generation
- Type-safe CLI parsing

### Mordant
KwinB uses [Mordant](https://github.com/ajalt/mordant) for pretty printing and Terminal UI
