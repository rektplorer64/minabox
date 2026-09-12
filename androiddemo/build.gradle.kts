plugins {
    // Since AGP 9 the Kotlin Android plugin is built in, applying
    // 'org.jetbrains.kotlin.android' explicitly is an error.
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    id("convention.jvm.toolchain")
}

android {
    // The namespace must be unique across all modules since AGP 9 - `:minabox` already uses
    // `eu.wewox.minabox`. The application id stays `eu.wewox.minabox`.
    namespace = "eu.wewox.minabox.androiddemo"

    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "eu.wewox.minabox"

        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()

        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":minabox"))
    implementation(project(":demo"))

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.uitooling)
    implementation(libs.compose.uitoolingpreview)
    implementation(libs.androidx.activitycompose)
    implementation(libs.androidx.splashscreen)
}
