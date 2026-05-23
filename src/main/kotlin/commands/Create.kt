package org.artificery.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import org.artificery.data.KotlinJvmProjectGenerator
import org.artificery.data.model.Project
import java.io.File

class Create(
    private val kotlinJvmProjectGenerator: KotlinJvmProjectGenerator = KotlinJvmProjectGenerator()
) : CliktCommand() {

    val projectName by argument()

    val outputDirectory by option("-o", "--outputDir", help = "Output directory for the project folder.")
        .file(mustExist = true, canBeFile = false, canBeDir = true)
        .default(File("."))

    override fun run() {
        kotlinJvmProjectGenerator.generate(
            project = Project(projectName),
            outputDirectory = outputDirectory.toPath(),
        )
    }
}