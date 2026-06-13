package org.artificery.data.generator

import org.artificery.data.PebbleTemplateRenderer
import org.artificery.data.model.Project
import java.nio.file.Files

class PlatformExecutablesBuildWorkflowGenerator(
    private val renderer: PebbleTemplateRenderer = PebbleTemplateRenderer(),
) {
    fun generate(project: Project) {
        with(project) {
            if (Files.exists(projectDirectoryPath).not()) {
                error("Project does not exist at directory: $projectDirectoryPath")
            }

            renderer.writeRenderedFile(
                targetFile = projectDirectoryPath.resolve(".github/actions/build-native-image/action.yml"),
                templatePath = "kotlin-jvm-cli/github/actions/build-native-image/action.yml.peb",
                context = context
            )

            renderer.writeRenderedFile(
                targetFile = projectDirectoryPath.resolve(".github/workflows/create-cli-executables.yml"),
                templatePath = "kotlin-jvm-cli/github/workflows/create-cli-executables.yml.peb",
                context = context
            )
        }
    }
}