package site.remlit.blueb.itemswitch.util

import org.bukkit.inventory.ItemStack
import java.util.UUID

data class InventoryItem(
    val uniqueId: UUID = UUID.randomUUID(),
    val position: Int,
    val item: ItemStack
)