package site.remlit.blueb.itemswitch

import org.bukkit.plugin.java.JavaPlugin
import org.bstats.bukkit.Metrics

class ItemSwitch : JavaPlugin() {
    override fun onEnable() {
        instance = this
        instance.server.pluginManager.registerEvents(ItemSwitchListener(), instance)
        Metrics(this, 26315)
    }

    override fun onDisable() { }

    companion object {
        lateinit var instance: JavaPlugin
    }
}
