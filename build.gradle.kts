plugins {
    id("java")
    id("java-library")
    id("jacoco")
}

group = "org.geektime.tdd"
version = "1.0-SNAPSHOT"

subprojects {

    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "jacoco")

    repositories {
        mavenLocal()
        maven {
            url = uri("https://maven.aliyun.com/repository/public/")
        }
        mavenCentral()
    }

    dependencies {
        implementation("jakarta.inject:jakarta.inject-api:2.0.1")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
        testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.8.2")
        testRuntimeOnly("org.junit.platform:junit-platform-runner:1.8.2")
        testImplementation("org.mockito:mockito-core:4.3.1")
        testImplementation("jakarta.inject:jakarta.inject-tck:2.0.1")
    }

    tasks.withType<Test>() {
        useJUnitPlatform()
    }

    configure<JavaPluginExtension>  {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}