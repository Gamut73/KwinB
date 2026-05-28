package org.artificery.data.model

enum class Feature(
    val featureName: String,
) {
    PlatformExecutables("Platform Executables");

    companion object {
        fun fromFeatureName(featureName: String): Feature? =
            entries.find { it.featureName == featureName }
    }
}