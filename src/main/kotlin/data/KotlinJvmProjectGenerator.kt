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

            if (Files.exists(projectDirectory)) {
                error("Directory already exists: $projectDirectory")
            }

            val context = mapOf(
                "projectName" to name,
                "packageId" to packageId,
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
                targetFile = projectDirectory.resolve("src/main/kotlin/Main.kt"),
                templatePath = "kotlin-jvm-cli/src/main/kotlin/Main.kt.peb",
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
}