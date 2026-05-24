package org.artificery.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.file
import org.artificery.data.generator.PlatformExecutablesBuildWorkflowGenerator
import org.artificery.data.model.Project
import kotlin.io.path.Path

class Add : CliktCommand() {
    override fun run() { }
}

class BuildPlatformExecutablesWorkflowFeature(
    private val platformExecutablesBuildWorkflowGenerator: PlatformExecutablesBuildWorkflowGenerator = PlatformExecutablesBuildWorkflowGenerator(),
) : CliktCommand(name = "platform-executables-workflow") {

    val projectDirectory by argument().file(mustExist = true, canBeFile = false, canBeDir = true)

    override fun run() {
        val projectName = projectDirectory.canonicalFile.name
        echo("Generating platform executables workflow for project: $projectName")
        platformExecutablesBuildWorkflowGenerator.generate(
            Project(projectName, projectFolderParentPath = Path(projectDirectory.canonicalPath).parent)
        )
    }
}