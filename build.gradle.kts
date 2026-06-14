plugins {
    kotlin("jvm") version "2.3.21"
    id("com.gradleup.shadow") version "9.4.1"
    application
}

application {
    mainClass.set("org.artificery.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {

    //CLIKT for cli
    implementation("com.github.ajalt.clikt:clikt:5.1.0")
    implementation("com.github.ajalt.clikt:clikt-markdown:5.1.0")

    //Mordant for TUI
    implementation("com.github.ajalt.mordant:mordant:3.0.2")

    //Pebble for template rendering
    implementation("io.pebbletemplates:pebble:4.1.1")

    //Haha
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    archiveBaseName.set("kwinb")
    archiveClassifier.set("")
    mergeServiceFiles()
    manifest {
        attributes("Main-Class" to "org.artificery.MainKt")
    }
}