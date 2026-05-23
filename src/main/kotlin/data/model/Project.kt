package org.artificery.data.model

data class Project(
    val name: String,
    val description: String,
    val group: String,
    val packageName: String,
    val kotlinVersion: String,
    val jdkVersion: Int,
)
