plugins {
    kotlin("jvm") version "2.3.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("Main.kt")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.github.ajalt.clikt:clikt:5.1.0")
    implementation("com.github.ajalt.clikt:clikt-markdown:5.1.0")

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}