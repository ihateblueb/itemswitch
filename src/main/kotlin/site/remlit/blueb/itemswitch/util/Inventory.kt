package site.remlit.blueb.itemswitch.util

import org.bukkit.inventory.ItemStack

// todo: add to lib later
data class Inventory(
    val hand: InventoryItem? = null,
    // todo: for lib, add offhand
    val content: List<InventoryItem>
) {
    /**
     * Determines if there's more than one InventoryItem in content that have the same ItemStack type
     * */
    fun hasMoreThanOne(item: ItemStack): Boolean {
        var count = 0
        for (it in content) {
            if (it.item.type == item.type) count++
            if (count > 1) break
        }
        return count > 1
    }

    /**
     * Determines if there's more than one InventoryItem in content that have the same ItemStack type
     * */
    fun hasMoreThanOne(item: InventoryItem): Boolean = hasMoreThanOne(item.item)

    companion object {
        fun from(inv: org.bukkit.inventory.PlayerInventory): Inventory {
            val items = mutableListOf<InventoryItem>()

            var pos = 0
            for (it in inv) {
                if (it == null) {
                    pos++
                    continue
                }
                items.add(
                    InventoryItem(
                        position = pos,
                        item = it,
                    )
                )
                pos++
            }

            return Inventory(
                hand = items.find { it.position == inv.heldItemSlot },
                content = items.toList()
            )
        }
    }
}