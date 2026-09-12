package eu.wewox.minabox

import androidx.compose.ui.unit.IntSize

/**
 * The information about the current layout of [MinaBox].
 *
 * Observe it with [MinaBoxState.layoutInfo] to react to the part of the content which is currently visible, for example
 * to track the items displayed to the user or to draw a scrollbar.
 */
public interface MinaBoxLayoutInfo {
    /**
     * The list of [MinaBoxItemInfo] representing all the currently visible items.
     *
     * An item is visible when its bounds overlap the viewport, so the size of this list is usually smaller than
     * [totalItemsCount]. The items are ordered by their [MinaBoxItemInfo.index] in ascending order.
     */
    public val visibleItemsInfo: List<MinaBoxItemInfo>

    /**
     * The total count of items passed to [MinaBox].
     *
     * It counts every item added with [MinaBoxScope.items], not only the visible ones.
     */
    public val totalItemsCount: Int

    /**
     * The size of the viewport in pixels.
     *
     * It is the size of the [MinaBox] bounds, and so it does not include the `contentPadding` of [MinaBox] - the
     * padding shifts the viewport within the plane instead of making it larger.
     */
    public val viewportSize: IntSize
        get() = IntSize.Zero
}
