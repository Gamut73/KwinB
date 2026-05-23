package org.artificery.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.types.file
import org.artificery.data.KotlinJvmProjectGenerator
import java.io.File

class Create(
    private val kotlinJvmProjectGenerator: KotlinJvmProjectGenerator = KotlinJvmProjectGenerator()
) : CliktCommand() {

    val projectName by argument()

    val outputDirectory by argument()
        .file(mustExist = true, canBeFile = false, canBeDir = true)
        .default(File("."))

    override fun run() {
        kotlinJvmProjectGenerator.generate(
            projectName = projectName,
            outputDirectory = outputDirectory.toPath()
        )
    }
}