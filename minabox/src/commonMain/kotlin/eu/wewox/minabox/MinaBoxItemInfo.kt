package eu.wewox.minabox

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size

/**
 * The information about a single item of [MinaBox].
 *
 * Instances are available with [MinaBoxLayoutInfo.visibleItemsInfo] for every item which is
 * currently visible. All the values are in pixels and are relative to the [MinaBox] bounds, so the
 * top left corner of the viewport is at (0, 0) regardless of the current translation.
 */
public interface MinaBoxItemInfo {
    /**
     * The index of the item in the list.
     *
     * It is the zero based position of the item among all the items added with [MinaBoxScope.items],
     * and the index used to resolve the layout, key and content type of the item.
     */
    public val index: Int

    /**
     * The key of the item which was passed to [MinaBoxScope.items].
     *
     * If no key was provided, a default key is generated from the [index] of the item.
     */
    public val key: Any

    /**
     * The offset of the item in pixels, relative to the top left corner of [MinaBox].
     *
     * It is the position at which the item is drawn, so it accounts for the current translation
     * along both axes and for the `contentPadding` of [MinaBox]. An item locked with
     * [MinaBoxItem.lockHorizontally] or [MinaBoxItem.lockVertically] ignores the translation along
     * the locked axis and keeps its position on the plane.
     */
    public val offset: Offset

    /**
     * The size of the item in pixels.
     *
     * The dimensions declared with [MinaBoxItem.Value] are already resolved, so an
     * [MinaBoxItem.Value.MatchParent] dimension is relative to the [MinaBox] size reduced by its
     * `contentPadding`.
     */
    public val size: Size

    /**
     * The bounds of the item in pixels, built from its [offset] and [size].
     */
    public val rect: Rect
        get() =
            Rect(
                offset = offset,
                size = size,
            )

    /**
     * The content type of the item which was passed to [MinaBoxScope.items], or `null` when it was
     * not provided.
     */
    public val contentType: Any?
        get() = null
}
