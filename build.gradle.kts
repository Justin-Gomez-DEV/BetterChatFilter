plugins {
    id("java-library")
    id("xyz.jpenilla.run-paper") version "3.0.2"
    id("com.gradleup.shadow") version "9.3.1"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven { url = uri("https://repo.panda-lang.org/releases") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.2.1")
    implementation("dev.rollczi:litecommands-bukkit:3.10.9")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    runServer {
        minecraftVersion("1.21")
        jvmArgs("-Xms2G", "-Xmx2G")
    }

    processResources {
        val props = mapOf("version" to version)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }

    shadowJar {
        archiveClassifier.set("")

        relocate(
            "org.bstats",
            "dev.gomez.java.betterChatFilter.libs.bstats"
        )

        relocate(
            "dev.rollczi.litecommands",
            "dev.gomez.java.betterChatFilter.libs.litecommands"
        )
    }

    jar {
        enabled = false
    }

    build{
        dependsOn(shadowJar)
    }

    compileJava {
        options.compilerArgs.add("-parameters")
    }
}
