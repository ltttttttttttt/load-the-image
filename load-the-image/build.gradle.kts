plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.compose") version composeVersion
    id("maven-publish")
}

group = "com.github.ltttttttttttt"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(compose.ui)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
}

publishing {
    publications {
        create("maven_public", MavenPublication::class) {
            from(components.getByName("java"))
        }
    }
}