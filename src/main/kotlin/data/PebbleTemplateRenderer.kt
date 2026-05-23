package org.artificery.data

import io.pebbletemplates.pebble.PebbleEngine
import io.pebbletemplates.pebble.loader.ClasspathLoader
import java.io.StringWriter

class PebbleTemplateRenderer {
    private val engine = PebbleEngine.Builder()
        .loader(
            ClasspathLoader().apply {
                prefix = "templates"
            }
        )
        .build()

    fun render(templatePath: String, context: Map<String, Any?>): String {
        val template = engine.getTemplate(templatePath)

        val writer = StringWriter()
        template.evaluate(writer, context)

        return writer.toString()
    }
}