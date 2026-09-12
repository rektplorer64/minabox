import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.mavenpublish)
    alias(libs.plugins.compose.compiler)
    id("convention.jvm.toolchain")
}

kotlin {
    explicitApi()

    // Use 'android' (or 'androidLibrary' on AGP 8.x) inside kotlin {}
    android {
        namespace = "eu.wewox.minabox"
        compileSdk = libs.versions.sdk.compile.get().toInt()
        minSdk = libs.versions.sdk.min.get().toInt()
    }

    jvm()

    iosArm64()
    iosSimulatorArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.library()
    }

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
        }

        all {
            languageSettings.optIn("androidx.compose.foundation.ExperimentalFoundationApi")
        }
    }
}