import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.cloud.tools.jib")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

group = "games.pixelfox"
version = "1.0.0-SNAPSHOT"
java.targetCompatibility = JavaVersion.VERSION_11
java.sourceCompatibility = JavaVersion.VERSION_11
jib.to.image = "tihlok/spring-player"
jib.to.auth.username = System.getenv("JIB_AUTH_USERNAME")
jib.to.auth.password = System.getenv("JIB_AUTH_PASSWORD")
jib.container.creationTime = "USE_CURRENT_TIMESTAMP"
extra["springCloudVersion"] = "2020.0.2"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":hellpers"))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.postgresql:postgresql:42.2.14")
    implementation("com.google.code.gson:gson:2.8.6")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    test {
        environment["SPRING_PROFILES_ACTIVE"] = "test"
    }
}
