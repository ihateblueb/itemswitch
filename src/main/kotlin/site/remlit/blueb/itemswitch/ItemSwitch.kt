package site.remlit.blueb.itemswitch

import org.bukkit.plugin.java.JavaPlugin

class ItemSwitch : JavaPlugin() {
    override fun onEnable() {
        instance = this
        instance.server.pluginManager.registerEvents(ItemSwitchListener(), instance)
    }

    override fun onDisable() { }

    companion object {
        lateinit var instance: JavaPlugin
    }
}
