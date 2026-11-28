plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "3.1.0"
    id("com.modrinth.minotaur") version "2.+"
    id("maven-publish")
}

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

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set(providers.gradleProperty("modrinth.projectId").get())

    versionName.set(project.version.toString())
    versionNumber.set(project.version.toString())
    versionType.set(providers.gradleProperty("modrinth.versionType").get())

    uploadFile.set(tasks.jar)

    gameVersions.add(libs.versions.minecraft)
    loaders.addAll(providers.gradleProperty("modrinth.loaders").get().split(','))

    syncBodyFrom.set(rootProject.file(providers.gradleProperty("modrinth.README").get()).readText())
}