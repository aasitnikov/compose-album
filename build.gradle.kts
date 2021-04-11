// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val compose_version by extra("1.0.0-beta04")
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha12")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0-M2-release-538")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}