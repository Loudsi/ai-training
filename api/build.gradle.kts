plugins {
    java
}

tasks.test { useJUnitPlatform() }

dependencies {
    implementation("org.loudsi.common:common-utils:1.0-SNAPSHOT")

    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-core:1.2.3")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.4")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}