package eu.wewox.minabox

import androidx.compose.foundation.lazy.layout.IntervalList
import androidx.compose.foundation.lazy.layout.MutableIntervalList
import androidx.compose.runtime.Composable

/**
 * Receiver scope which is used by [MinaBox].
 */
public interface MinaBoxScope {

    /**
     * Adds a [count] of items.
     *
     * @param count The items count.
     * @param layoutInfo The lambda to provide layout information of the single item.
     * @param key A factory of stable and unique keys representing the item. Using the same key for multiple items is
     *    not allowed. Type of the key should be saveable via Bundle on Android. If null is passed the position in the
     *    list will represent the key.
     * @param contentType A factory of the content types for the item. The item compositions of the same type could be
     *    reused more efficiently. Note that null is a valid type and items of such type will be considered compatible.
     * @param metadata A factory of additional information for the item. The value is opaque to the layout and is never
     *    read while resolving the lazy layout, so it does not affect which items are measured, placed or composed. It
     *    is only exposed to the API user through [MinaBoxItemInfo.metadata] and [MinaBoxState.getMetadata]. Note that
     *    null is a valid value and means that the item has no additional information.
     * @param itemContent The content displayed by a single item.
     */
    public fun items(
        count: Int,
        layoutInfo: (index: Int) -> MinaBoxItem,
        key: ((index: Int) -> Any)? = null,
        contentType: (index: Int) -> Any? = { null },
        metadata: ((index: Int) -> Any?)? = null,
        itemContent: @Composable (index: Int) -> Unit
    )

    /**
     * Adds given [items].
     *
     * @param items The items to add to the [MinaBox].
     * @param layoutInfo The lambda to provide layout information of the single item.
     * @param key A factory of stable and unique keys representing the item. Using the same key for multiple items is
     *    not allowed. Type of the key should be saveable via Bundle on Android. If null is passed the position in the
     *    list will represent the key.
     * @param contentType A factory of the content types for the item. The item compositions of the same type could be
     *    reused more efficiently. Note that null is a valid type and items of such type will be considered compatible.
     * @param metadata A factory of additional information for the item, invoked with the item itself. The value is
     *    opaque to the layout and is never read while resolving the lazy layout, so it does not affect which items are
     *    measured, placed or composed. It is only exposed to the API user through [MinaBoxItemInfo.metadata] and
     *    [MinaBoxState.getMetadata]. Note that null is a valid value and means that the item has no additional
     *    information.
     * @param itemContent The content displayed by a single item.
     */
    public fun <T> items(
        items: List<T>,
        layoutInfo: (item: T) -> MinaBoxItem,
        key: ((item: T) -> Any)? = null,
        contentType: (item: T) -> Any? = { null },
        metadata: ((item: T) -> Any?)? = null,
        itemContent: @Composable (item: T) -> Unit
    ): Unit = items(
        count = items.size,
        layoutInfo = { index: Int -> layoutInfo(items[index]) },
        key = if (key != null) { index: Int -> key(items[index]) } else null,
        contentType = { index: Int -> contentType(items[index]) },
        metadata = if (metadata != null) { index: Int -> metadata(items[index]) } else null,
        itemContent = { index: Int -> itemContent(items[index]) },
    )
}

/**
 * Implementation of the [MinaBoxScope] with [IntervalList].
 */
internal class MinaBoxScopeImpl : MinaBoxScope {

    private val _intervals = MutableIntervalList<MinaBoxItemContent>()

    /**
     * Registered items in the [MinaBox].
     */
    val intervals: IntervalList<MinaBoxItemContent> = _intervals

    override fun items(
        count: Int,
        layoutInfo: (index: Int) -> MinaBoxItem,
        key: ((index: Int) -> Any)?,
        contentType: (index: Int) -> Any?,
        metadata: ((index: Int) -> Any?)?,
        itemContent: @Composable (index: Int) -> Unit
    ) {
        _intervals.addInterval(
            count,
            MinaBoxItemContent(
                key = key,
                contentType = contentType,
                layoutInfo = layoutInfo,
                metadata = metadata,
                item = itemContent
            )
        )
    }
}

internal class MinaBoxItemContent(
    val key: ((index: Int) -> Any)?,
    val contentType: (index: Int) -> Any?,
    val layoutInfo: (index: Int) -> MinaBoxItem,
    val metadata: ((index: Int) -> Any?)?,
    val item: @Composable (index: Int) -> Unit
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MinaBoxItemContent) return false

        if (key != other.key) return false
        if (contentType != other.contentType) return false
        if (layoutInfo != other.layoutInfo) return false
        if (metadata != other.metadata) return false
        if (item != other.item) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + contentType.hashCode()
        result = 31 * result + layoutInfo.hashCode()
        result = 31 * result + metadata.hashCode()
        result = 31 * result + item.hashCode()
        return result
    }

    override fun toString(): String {
        return "MinaBoxItemContent(key=$key, contentType=$contentType, layoutInfo=$layoutInfo, metadata=$metadata, item=$item)"
    }
}
