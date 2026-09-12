package eu.wewox.minabox

import androidx.compose.ui.unit.IntSize

internal data class MinaBoxLayoutInfoImpl(
    override val visibleItemsInfo: List<MinaBoxItemInfo>,
    override val totalItemsCount: Int,
    override val viewportSize: IntSize,
) : MinaBoxLayoutInfo
