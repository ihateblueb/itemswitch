package site.remlit.blueb.itemswitch

import org.bukkit.NamespacedKey
import org.bukkit.OfflinePlayer
import org.bukkit.persistence.PersistentDataType

class PreferenceService {
    companion object {
        private val toggleKey: NamespacedKey = NamespacedKey(ItemSwitch.instance, "preference.toggled")

        private val toggleBlockPlaceKey: NamespacedKey = NamespacedKey(ItemSwitch.instance, "preference.toggled.blockplace")
        private val toggleItemBreakKey: NamespacedKey = NamespacedKey(ItemSwitch.instance, "preference.toggled.itembreak")
        private val toggleItemConsumeKey: NamespacedKey = NamespacedKey(ItemSwitch.instance, "preference.toggled.itemconsume")
        private val toggleBucketEmptyKey: NamespacedKey = NamespacedKey(ItemSwitch.instance, "preference.toggled.bucketempty")
        private val toggleDropItemKey: NamespacedKey = NamespacedKey(ItemSwitch.instance, "preference.toggled.dropitem")

        private fun getKey(eventName: String?) = when (eventName) {
            "BlockPlaceEvent" -> toggleBlockPlaceKey
            "PlayerItemBreakEvent" -> toggleItemBreakKey
            "PlayerItemConsumeEvent" -> toggleItemConsumeKey
            "PlayerBucketEmptyEvent" -> toggleBucketEmptyKey
            "PlayerDropItemEvent" -> toggleDropItemKey
            else -> toggleKey
        }

        private fun getConfigKey(eventName: String) = when (eventName) {
            "BlockPlaceEvent" -> "blockPlace"
            "PlayerItemBreakEvent" -> "itemBreak"
            "PlayerItemConsumeEvent" -> "itemConsume"
            "PlayerBucketEmptyEvent" -> "bucketEmpty"
            "PlayerDropItemEvent" -> "dropItem"
            else -> ""
        }

        fun isEnabled(player: OfflinePlayer, eventName: String? = null): Boolean {
            if (player.player == null) throw Exception("Player cannot be null!")
            val pdc = player.player!!.persistentDataContainer
            return if (eventName != null) {
                pdc.get(getKey(eventName), PersistentDataType.BOOLEAN) ?: ItemSwitch.instance.config.getBoolean(getConfigKey(eventName)) as Boolean? ?: true
            } else pdc.get(toggleKey, PersistentDataType.BOOLEAN) ?: true
        }

        fun toggleEnabled(player: OfflinePlayer, eventName: String? = null): Boolean {
            if (player.player == null) throw Exception("Player cannot be null!")
            val pdc = player.player!!.persistentDataContainer
            pdc.set(getKey(eventName), PersistentDataType.BOOLEAN, !isEnabled(player, eventName))
            return isEnabled(player, eventName)
        }
    }
}