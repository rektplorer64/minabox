package eu.wewox.minabox

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    // Compose Multiplatform 1.12 replaced `CanvasBasedWindow` with `ComposeViewport`. It creates its
    // own `<canvas>` inside the given container element instead of reusing an existing one.
    ComposeViewport(viewportContainerId = "composeTarget") {
        App()
    }
}
