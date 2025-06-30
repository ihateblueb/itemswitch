package site.remlit.blueb.itemswitch

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerBucketEmptyEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack
import site.remlit.blueb.itemswitch.util.Inventory

class ItemSwitchListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onBlockPlace(event: BlockPlaceEvent) = replace(event.player)

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerItemBreak(event: PlayerItemBreakEvent) = replace(event.player)

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerItemConsume(event: PlayerItemConsumeEvent) = replace(event.player)

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerBucketEmpty(event: PlayerBucketEmptyEvent) = replace(event.player)

    // todo: test
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerDropItem(event: PlayerDropItemEvent) = replace(event.player)

    private val buckets: List<Material> = listOf(
        Material.WATER_BUCKET,

        Material.COD_BUCKET,
        Material.SALMON_BUCKET,
        Material.TROPICAL_FISH_BUCKET,
        Material.PUFFERFISH_BUCKET,
        Material.AXOLOTL_BUCKET,
        Material.TADPOLE_BUCKET,

        Material.LAVA_BUCKET,
        Material.POWDER_SNOW_BUCKET,
        Material.MILK_BUCKET,
    )

    private val bottles: List<Material> = listOf(
        Material.POTION,
        Material.HONEY_BOTTLE,
    )

    private fun replace(player: Player) {
        if (!PreferenceService.isEnabled(player))
            return

        val inventory = Inventory.from(player.inventory)
        val hand = inventory.hand

        if (hand == null || hand.item.amount > 1 || !inventory.hasMoreThanOne(hand))
            return

        for (it in inventory.content) {
            if (it.uniqueId == hand.uniqueId) continue
            if (it.item.type == hand.item.type) {
                val isBucket = buckets.contains(hand.item.type)
                val isBottle = bottles.contains(hand.item.type)

                if (isBucket || isBottle) {
                    val emptyItem = ItemStack(
                        if (isBucket) Material.BUCKET
                        else Material.GLASS_BOTTLE
                    )

                    player.inventory.setItem(it.position, emptyItem)
                    Bukkit.getScheduler().scheduleSyncDelayedTask(ItemSwitch.instance, {
                        player.inventory.setItem(hand.position, it.item)
                    }, 1L)
                    break
                } else {
                    println("ELSE")
                    // tools, blocks, food, other consumables without containers
                    player.inventory.setItem(it.position, ItemStack(Material.AIR))
                    player.inventory.setItem(hand.position, it.item)
                    break
                }
            }
        }
    }
}