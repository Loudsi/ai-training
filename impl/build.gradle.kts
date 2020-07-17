plugins {
    java
}


tasks.test { useJUnitPlatform() }
dependencies {
    implementation("org.loudsi.common:common-utils:1.0-SNAPSHOT")
    implementation(project(":api"))
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.4")
}