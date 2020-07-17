allprojects {
    group = "org.loudsi.simulation"
    repositories {
        mavenCentral()
        jcenter()
        maven {
            name = "GitHubPackagesCommon"
            url = uri("https://maven.pkg.github.com/Loudsi/common-utils")
            credentials {
                username = project.findProperty("GITHUB_USERNAME") as String? ?: System.getenv("GITHUB_USERNAME")
                password = project.findProperty("GITHUB_TOKEN") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
