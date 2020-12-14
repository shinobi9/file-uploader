plugins {
    kotlin("jvm") version "1.4.10"
}

group = "cyou.shinobi9"
version = "0.0.1"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/central")
    mavenCentral()
    jcenter()
}
dependencies {
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.10")
    implementation("io.github.rybalkinsd:kohttp:0.12.0")
    implementation("io.github.rybalkinsd:kohttp-jackson:0.12.0")
    implementation("io.github.microutils:kotlin-logging:1.12.0")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
