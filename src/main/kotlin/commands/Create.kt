package org.artificery.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import com.github.ajalt.mordant.input.interactiveMultiSelectList
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.terminal.success
import org.artificery.data.generator.KotlinJvmProjectGenerator
import org.artificery.data.generator.PlatformExecutablesBuildWorkflowGenerator
import org.artificery.data.model.Feature
import org.artificery.data.model.Project
import java.io.File

class Create(
    private val kotlinJvmProjectGenerator: KotlinJvmProjectGenerator = KotlinJvmProjectGenerator(),
    private val platformExecutablesBuildWorkflowGenerator: PlatformExecutablesBuildWorkflowGenerator = PlatformExecutablesBuildWorkflowGenerator(),
) : CliktCommand() {

    val projectName by argument()

    val outputDirectory by option("-o", "--outputDir", help = "Output directory for the project folder.")
        .file(mustExist = true, canBeFile = false, canBeDir = true)
        .default(File("."))

    override fun run() {
        kotlinJvmProjectGenerator.generate(
            project = Project(projectName, outputDirectory.toPath()),
        )

        echo("Successfully created $projectName")
        chooseExtraFeatures()
    }

    private fun chooseExtraFeatures() {
        val terminal = Terminal()

        val selection = terminal.interactiveMultiSelectList(
            Feature.entries.map { it.featureName },
            title = "Select Optional Features",
        )
        if (selection.isNullOrEmpty()) {
            terminal.success("Proceeding with no extra features.")
        } else {
            terminal.success("Adding the following features ${TextColors.blue(selection.joinToString(","))}")

            selection.map { Feature.fromFeatureName(it) }.forEach { feature ->
                when (feature) {
                    Feature.PlatformExecutables -> {
                        platformExecutablesBuildWorkflowGenerator.generate(
                            project = Project(projectName, outputDirectory.toPath())
                        )
                    }

                    else -> Unit
                }

                terminal.success("Successfully added selected features")
            }
        }
    }
}