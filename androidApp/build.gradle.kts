plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "ru.kontur.kickerchamp.android"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.2"
    }
}

dependencies {
    implementation(project(":shared"))
    // AndroidX
    implementation(libs.androidCoreKtx)
    implementation(libs.androidActivityKtx)
    implementation(libs.androidLifecycleKtx)
    implementation(libs.androidViewModelKtx)
    implementation(libs.androidMaterial)

    // Jetpack Compose
    implementation(libs.composeUi)
    implementation(libs.composeMaterial)
    implementation(libs.composeToolingPreview)
    implementation(libs.composeActivity)
    implementation(libs.composeFoundation)
    implementation(libs.composeNavigation)
    implementation(libs.composeInsets)
    debugImplementation(libs.composeTooling)

    // Kotlin coroutines
    implementation(libs.kotlinCoroutines)
    implementation(libs.kotlinCoroutinesAndroid)

    // Koin DI
    implementation(libs.koin)
    implementation(libs.koinAndroid)
    implementation(libs.koinCompose)
}
