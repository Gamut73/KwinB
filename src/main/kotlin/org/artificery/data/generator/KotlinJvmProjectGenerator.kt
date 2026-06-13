package org.artificery.data.generator

import org.artificery.data.PebbleTemplateRenderer
import org.artificery.data.isValid
import org.artificery.data.model.Project
import java.nio.file.Files

class KotlinJvmProjectGenerator(
    private val renderer: PebbleTemplateRenderer = PebbleTemplateRenderer(),
) {
    fun generate(project: Project) {
        with(project) {
            if (Files.exists(projectDirectoryPath)) {
                error("Directory already exists: $projectDirectoryPath")
            } else if (project.isValid().not()) {
                error("Project is not valid: $project, please fix the validation errors and try again.")
            }

            renderer.writeRenderedFile(
                targetFile = projectDirectoryPath.resolve(".gitignore"),
                templatePath = "kotlin-jvm-cli/.gitignore.peb",
                context = context
            )

            renderer.writeRenderedFile(
                targetFile = projectDirectoryPath.resolve("settings.gradle.kts"),
                templatePath = "kotlin-jvm-cli/settings.gradle.kts.peb",
                context = context
            )

            renderer.writeRenderedFile(
                targetFile = projectDirectoryPath.resolve("build.gradle.kts"),
                templatePath = "kotlin-jvm-cli/build.gradle.kts.peb",
                context = context
            )

            renderer.writeRenderedFile(
                targetFile = projectDirectoryPath.resolve("$srcDirectoryFilePath/Main.kt"),
                templatePath = "kotlin-jvm-cli/src/main/kotlin/Main.kt.peb",
                context = context
            )

            renderer.writeRenderedFile(
                targetFile = projectDirectoryPath.resolve("$srcDirectoryFilePath/commands/$name.kt"),
                templatePath = "kotlin-jvm-cli/src/main/kotlin/commands/Project.kt.peb",
                context = context
            )
        }
    }
}