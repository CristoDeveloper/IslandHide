plugins {
    `java-library`
    id("io.github.goooler.shadow") version "8.1.8"
    id("xyz.jpenilla.run-paper") version "2.3.0" // Useful for testing
}

group = "net.solryn"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    compileOnly("net.dmulloy2:ProtocolLib:5.4.0")
    
    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("com.github.seeseemelk:MockBukkit-v1.21:3.94.0") // MockBukkit for 1.21
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
    
    javadoc {
        options.encoding = "UTF-8"
    }

    shadowJar {
        archiveClassifier.set("")
        // Relocate dependencies if we add any shaded ones later
        // relocate("co.aikar.commands", "net.solryn.islandhide.acf")
    }
    
    processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}
