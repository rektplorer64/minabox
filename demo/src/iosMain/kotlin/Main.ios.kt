import androidx.compose.ui.window.ComposeUIViewController
import eu.wewox.minabox.App

// This name is part of the iOS framework's Objective-C API and is called from ContentView.swift,
// so it intentionally does not follow the Kotlin function naming convention.
@Suppress("ktlint:standard:function-naming")
fun MainViewController() = ComposeUIViewController { App() }
