pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "KickerChamp"
include(":androidApp")
include(":shared")

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val ktxVersion = "2.3.1"
            val composeVersion = "1.0.2"
            val coroutinesVersion = "1.5.1"
            val koinVersion = "3.1.2"
            val sqlDelightVersion = "1.5.0"

            alias("androidCoreKtx").to("androidx.core:core-ktx:1.6.0")
            alias("androidActivityKtx").to("androidx.activity:activity-ktx:1.3.0")
            alias("androidViewModelKtx").to("androidx.lifecycle:lifecycle-viewmodel-ktx:$ktxVersion")
            alias("androidLifecycleKtx").to("androidx.lifecycle:lifecycle-runtime-ktx:$ktxVersion")
            alias("androidMaterial").to("com.google.android.material:material:1.4.0")

            alias("composeUi").to("androidx.compose.ui:ui:$composeVersion")
            alias("composeTooling").to("androidx.compose.ui:ui-tooling:$composeVersion")
            alias("composeToolingPreview").to("androidx.compose.ui:ui-tooling-preview:$composeVersion")
            alias("composeMaterial").to("androidx.compose.material:material:$composeVersion")
            alias("composeFoundation").to("androidx.compose.foundation:foundation:$composeVersion")
            alias("composeActivity").to("androidx.activity:activity-compose:1.3.1")
            alias("composeNavigation").to("androidx.navigation:navigation-compose:2.4.0-alpha08")
            alias("composeInsets").to("com.google.accompanist:accompanist-insets:0.18.0")

            alias("kotlinCoroutines").to("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            alias("kotlinCoroutinesAndroid").to("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

            alias("koin").to("io.insert-koin:koin-core:$koinVersion")
            alias("koinAndroid").to("io.insert-koin:koin-android:$koinVersion")
            alias("koinCompose").to("io.insert-koin:koin-androidx-compose:$koinVersion")

            alias("sqlDelightRuntime").to("com.squareup.sqldelight:runtime:$sqlDelightVersion")
            alias("sqlDelightAndroid").to("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            alias("sqlDelightNative").to("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            alias("sqlDelightCoroutinesExtensions").to("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")
        }
    }
}