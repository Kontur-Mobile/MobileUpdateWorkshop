import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()
    jvm("desktop")

    val xcf = XCFramework()

    ios {
        binaries {
            framework {
                baseName = "shared"
                xcf.add(this)
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.sqlDelightRuntime)
                implementation(libs.sqlDelightCoroutinesExtensions)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt")
                implementation(libs.koin)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelightAndroid)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.sqlDelightNative)
            }
        }
        val iosTest by getting

        val desktopMain by getting {
            dependencies {
                implementation(libs.sqlDelightDesktop)
            }
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "ru.kontur.kickerchamp.db"
    }
}