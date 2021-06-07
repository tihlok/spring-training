rootProject.name = "player"

pluginManagement {
    plugins {
        id("org.springframework.boot") version "2.4.5"
        id("io.spring.dependency-management") version "1.0.11.RELEASE"
        id("com.google.cloud.tools.jib") version "3.0.0"
        kotlin("jvm") version "1.4.32"
        kotlin("plugin.spring") version "1.4.32"
        kotlin("plugin.jpa") version "1.4.32"
    }
}

include(":hellpers")
project(":hellpers").projectDir = File(settingsDir, "../hellpers")
