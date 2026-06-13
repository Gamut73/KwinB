package org.artificery.data

import io.pebbletemplates.pebble.PebbleEngine
import io.pebbletemplates.pebble.loader.ClasspathLoader
import java.io.StringWriter
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class PebbleTemplateRenderer {
    private val engine = PebbleEngine.Builder()
        .loader(
            ClasspathLoader().apply {
                prefix = "templates"
            }
        )
        .newLineTrimming(false)
        .build()

    fun writeRenderedFile(
        targetFile: Path,
        templatePath: String,
        context: Map<String, Any?>
    ) {
        targetFile.parent.createDirectories()

        val renderedContent = render(templatePath, context)

        targetFile.writeText(renderedContent)
    }

    private fun render(templatePath: String, context: Map<String, Any?>): String {
        val template = engine.getTemplate(templatePath)

        val writer = StringWriter()
        template.evaluate(writer, context)

        return writer.toString()
    }
}