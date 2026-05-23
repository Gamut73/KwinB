plugins {
    kotlin("jvm") version "2.3.21"
    id("com.gradleup.shadow") version "9.4.1"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("MainKt")
    applicationDefaultJvmArgs = listOf("-Djava.util.logging.config.file=logging.properties")
}

repositories {
    mavenCentral()
}

dependencies {

    //CLIKT for cli
    implementation("com.github.ajalt.clikt:clikt:5.1.0")
    implementation("com.github.ajalt.clikt:clikt-markdown:5.1.0")

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