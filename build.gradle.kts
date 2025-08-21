plugins {
    id("org.jetbrains.intellij.platform") version "2.7.2"
    kotlin("jvm") version "2.2.10"
}

group = "com.sakshamc"
version = "0.0.8"

repositories {
    mavenCentral()
}

repositories {
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2025.2")
    }
}


tasks {
    patchPluginXml {
        sinceBuild.set("232")
    }
}
