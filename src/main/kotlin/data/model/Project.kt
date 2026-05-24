package org.artificery.data.model

import java.nio.file.Path

data class Project(
    val name: String,
    val outputDirectoryPath: Path = Path.of("."),
    val packageId: String = "org.artificery",
    val groupId: String = "org.artificery",
    val projectDirectoryPath: Path = outputDirectoryPath.resolve(name),
    val srcDirectoryFilePath: String = "src/main/kotlin/${packageIdToPath(packageId.lowercase())}",
    val context: Map<String, String> = mapOf(
        "projectName" to name,
        "packageId" to packageId.lowercase(),
        "groupId" to groupId.lowercase(),
    )
) {
    companion object {
        private fun packageIdToPath(packageId: String): String = packageId.replace(".", "/")
    }
}
