plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "3.1.0"
    id("maven-publish")
}

group = "com.github.assemblyorg.keepInventory"
version = "26.3-1.0"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.paperapi)
}

tasks {
    runServer {
        minecraftVersion(libs.versions.minecraft.get())
        jvmArgs("-Xms2G", "-Xmx2G", "-Dcom.mojang.eula.agree=true")
    }

    processResources {
        val properties = mapOf(
            "name" to rootProject.name,
            "version" to project.version,
            "minecraft" to libs.versions.minecraft.get(),
        )

        inputs.properties(properties)
        filteringCharset = "UTF-8"
        filesMatching("paper-plugin.yml") {
            expand(properties)
        }
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
