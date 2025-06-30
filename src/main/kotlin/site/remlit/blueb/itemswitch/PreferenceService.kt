package site.remlit.blueb.itemswitch

import org.bukkit.NamespacedKey
import org.bukkit.OfflinePlayer
import org.bukkit.persistence.PersistentDataType

class PreferenceService {
    companion object {
        private val toggleKey: NamespacedKey? = NamespacedKey.fromString("site.remlit.blueb.itemswitch.preference.toggled")

        fun isEnabled(player: OfflinePlayer): Boolean {
            if (toggleKey == null) throw Exception("toggleKey is null")
            if (player.player == null) throw Exception("Player cannot be null!")
            val pdc = player.player!!.persistentDataContainer
            return pdc.get(toggleKey, PersistentDataType.BOOLEAN) ?: true
        }

        fun toggleEnabled(player: OfflinePlayer): Boolean {
            if (toggleKey == null) throw Exception("toggleKey is null")
            if (player.player == null) throw Exception("Player cannot be null!")
            val pdc = player.player!!.persistentDataContainer
            pdc.set(toggleKey, PersistentDataType.BOOLEAN, !isEnabled(player))
            return isEnabled(player)
        }
    }
}