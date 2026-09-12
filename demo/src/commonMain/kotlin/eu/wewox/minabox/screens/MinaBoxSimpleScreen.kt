package eu.wewox.minabox.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import eu.wewox.minabox.Example
import eu.wewox.minabox.MinaBox
import eu.wewox.minabox.MinaBoxItem
import eu.wewox.minabox.ui.components.TopBar

/**
 * Simple Mina Box layout example.
 */
@Composable
fun MinaBoxSimpleScreen(
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = Example.MinaBoxSimple.label,
                onBackClick = onBackClick,
            )
        },
    ) { padding ->
        val itemSizePx = with(LocalDensity.current) { ITEM_SIZE.toSize() }
        MinaBox(modifier = Modifier.padding(padding)) {
            items(
                count = COLUMNS_COUNT * ROWS_COUNT,
                layoutInfo = {
                    val column = it % COLUMNS_COUNT
                    val row = it / COLUMNS_COUNT
                    MinaBoxItem(
                        x = itemSizePx.width * column,
                        y = itemSizePx.height * row,
                        width = itemSizePx.width,
                        height = itemSizePx.height,
                    )
                },
            ) { index ->
                Text(
                    text = "Index #$index",
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                        .padding(8.dp),
                )
            }
        }
    }
}

private const val COLUMNS_COUNT = 50
private const val ROWS_COUNT = 50
private val ITEM_SIZE = DpSize(144.dp, 48.dp)
