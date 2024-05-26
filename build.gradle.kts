// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.android.library") version "8.1.4" apply false
    id("com.diffplug.spotless") version "6.19.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target(
                fileTree(".") {
                    include("**/*.kt")
                    exclude("spotless/copyright.kt", "**/build/**")
                },
            )
            ktlint()
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("$buildDir/**/*.kts")
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"), "(^(?![\\/ ]\\*).*$)")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }

    afterEvaluate {
        tasks.named("preBuild") {
            dependsOn("spotlessApply")
        }
    }
}

