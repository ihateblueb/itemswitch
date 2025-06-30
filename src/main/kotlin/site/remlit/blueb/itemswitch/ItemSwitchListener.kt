package site.remlit.blueb.itemswitch

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack

class ItemSwitchListener : Listener {
    @EventHandler
    fun onBlockPlaceEvent(event: BlockPlaceEvent) = replace(event.player)

    @EventHandler
    fun onPlayerItemBreakEvent(event: PlayerItemBreakEvent) = replace(event.player)

    @EventHandler
    fun onPlayerItemConsumeEvent(event: PlayerItemConsumeEvent) = replace(event.player)

    /*
    * PlayerDropItemEvent?
    * */


    private fun replace(player: Player) {
        val item = player.inventory.itemInMainHand

        if (item.amount > 1 || !player.inventory.contains(item.type))
            return

        val inventory = mutableListOf<ItemStack>()

        player.inventory.contents.forEach { it -> if (it != null && it != item) inventory.add(it) }

        for (it in inventory) {
            if (it.type == item.type) {
                player.inventory.remove(it)
                player.inventory.setItemInMainHand(it)
                break
            }
        }
    }
}