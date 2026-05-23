package org.artificery.data

import org.artificery.data.model.Project
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class KotlinJvmProjectGenerator(
    private val renderer: PebbleTemplateRenderer = PebbleTemplateRenderer()
) {
    fun generate(project: Project, outputDirectory: Path) {
        with(project) {
            val projectDirectory = outputDirectory.resolve(name)
            val srcDirectoryFilePath = "src/main/kotlin/${packageIdToPath(packageId.lowercase())}"

            if (Files.exists(projectDirectory)) {
                error("Directory already exists: $projectDirectory")
            } else if (project.isValid().not()) {
                error("Project is not valid: $project, please fix the validation errors and try again.")
            }

            val context = mapOf(
                "projectName" to name,
                "packageId" to packageId.lowercase(),
                "groupId" to groupId.lowercase(),
            )

            writeRenderedFile(
                targetFile = projectDirectory.resolve("settings.gradle.kts"),
                templatePath = "kotlin-jvm-cli/settings.gradle.kts.peb",
                context = context
            )

            writeRenderedFile(
                targetFile = projectDirectory.resolve("build.gradle.kts"),
                templatePath = "kotlin-jvm-cli/build.gradle.kts.peb",
                context = context
            )

            writeRenderedFile(
                targetFile = projectDirectory.resolve("$srcDirectoryFilePath/Main.kt"),
                templatePath = "kotlin-jvm-cli/src/main/kotlin/Main.kt.peb",
                context = context
            )

            writeRenderedFile(
                targetFile = projectDirectory.resolve("$srcDirectoryFilePath/commands/$name.kt"),
                templatePath = "kotlin-jvm-cli/src/main/kotlin/commands/Project.kt.peb",
                context = context
            )
        }
    }

    private fun writeRenderedFile(
        targetFile: Path,
        templatePath: String,
        context: Map<String, Any?>
    ) {
        targetFile.parent.createDirectories()

        val renderedContent = renderer.render(templatePath, context)

        targetFile.writeText(renderedContent)
    }

    private fun packageIdToPath(packageId: String): String = packageId.replace(".", "/")
}