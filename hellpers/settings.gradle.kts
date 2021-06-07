rootProject.name = "hellpers"

pluginManagement {
    plugins {
        id("org.springframework.boot") version "2.4.5"
        id("io.spring.dependency-management") version "1.0.11.RELEASE"
        kotlin("jvm") version "1.4.32"
        kotlin("plugin.spring") version "1.4.32"
        kotlin("plugin.jpa") version "1.4.32"
    }
}
