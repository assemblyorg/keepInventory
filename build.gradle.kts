plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "3.0.1"
    id("maven-publish")
}

group = "com.github.assemblyorg.keepInventory"
version = "2.0.0"

val java = 21
val minecraftVersion = "1.21.11"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(java)
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release = java
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:$minecraftVersion-R0.1-SNAPSHOT")
}

tasks.runServer {
    minecraftVersion(minecraftVersion)
}

tasks.processResources {
    val properties = mapOf(
        "name" to rootProject.name,
        "version" to project.version,
        "main" to "${project.group}.KeepInventory",
        "minecraft_version" to minecraftVersion,
    )
    inputs.properties(properties)
    filteringCharset = "UTF-8"
    filesMatching("paper-plugin.yml") {
        expand(properties)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "core"
            version = project.version.toString()
            from(components["java"])
        }
    }
}
