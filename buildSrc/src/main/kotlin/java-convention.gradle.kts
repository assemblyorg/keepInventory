plugins {
    id("java")
    com.gradleup.shadow
}

val java = 25

base {
    archivesName = "${rootProject.name}-${project.name}"
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(java)
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release = java
}

tasks.jar {
    enabled = false
}

tasks.shadowJar {
    archiveClassifier = ""
}