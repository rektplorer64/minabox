import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        // Kotlin 2.4 removed the `moduleName` target property in favor of `outputModuleName`.
        outputModuleName.set("minabox-demo")
        browser {
            commonWebpackConfig {
                outputFileName = "main.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    port = 8080
                    // The `static` list property is deprecated, use the `static(...)` function.
                    static(project.rootDir.path)
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        wasmJsMain.dependencies {
            implementation(project(":demo"))
            implementation(compose.ui)
        }
    }
}
