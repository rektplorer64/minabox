import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.cocoapods)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    id("convention.jvm.toolchain")
}

kotlin {
    // With the AGP 9 KMP library plugin the Android target is configured inside `kotlin {}`.
    android {
        namespace = "eu.wewox.minabox.demo"
        compileSdk = libs.versions.sdk.compile.get().toInt()
        minSdk = libs.versions.sdk.min.get().toInt()
    }

    jvm()

    // Compose Multiplatform 1.12 no longer publishes `iosX64` artifacts.
    iosArm64()
    iosSimulatorArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.library()
    }

    cocoapods {
        version = "1.0.0"
        summary = "Demo Compose Multiplatform module"
        homepage = "---"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosdemo/Podfile")
        framework {
            baseName = "demo"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":minabox"))
            implementation(compose.material3)
            // Compose Multiplatform 1.12 no longer brings the Material icons in transitively.
            implementation(libs.compose.material.icons.core)
        }

        all {
            languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
        }
    }
}
