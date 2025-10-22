description = "Server Content"

dependencies {
    implementation(projects.util)
    implementation(project(":game-api"))
    implementation(project(":game-server"))
    implementation(kotlin("script-runtime"))
    implementation(kotlin("scripting-jvm"))
    implementation(kotlin("scripting-jvm-host"))
}

tasks.named<Jar>("jar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}