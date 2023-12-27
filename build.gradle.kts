plugins {
    kotlin("multiplatform") version "1.9.22" apply false
}

subprojects {
    repositories {
        maven("https://oss.sonatype.org/content/groups/staging")
        mavenCentral()
    }

    plugins.apply("org.jetbrains.kotlin.multiplatform")

    configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
        @Suppress("OPT_IN_USAGE")
        wasmJs {
            binaries.executable()
            browser()
            applyBinaryen()
        }

        sourceSets {
            commonMain {
                dependencies {
                    implementation("com.jamesward:looperkt:0.0.3")
                }
            }
        }
    }
}