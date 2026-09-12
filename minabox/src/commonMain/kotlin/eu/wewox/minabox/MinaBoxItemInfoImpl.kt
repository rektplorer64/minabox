package eu.wewox.minabox

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

internal data class MinaBoxItemInfoImpl(
    override val index: Int,
    override val key: Any,
    override val offset: Offset,
    override val size: Size,
    override val contentType: Any?,
) : MinaBoxItemInfo
