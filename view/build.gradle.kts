plugins {
    java
    id("org.springframework.boot") version "2.3.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.openjfx.javafxplugin") version "0.0.8"
}

javafx {
    version = "13"
    modules("javafx.controls", "javafx.fxml")
}


tasks.test { useJUnitPlatform() }
dependencies {
    implementation("org.loudsi.common:common-utils:1.0-SNAPSHOT")

    implementation(project(":api"))
    implementation(project(":impl"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.4")
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
