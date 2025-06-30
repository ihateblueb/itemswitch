package site.remlit.blueb.itemswitch

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

@Suppress("Unused")
@CommandAlias("itemswitch|is")
class ItemSwitchCommand : BaseCommand() {
    @Default
    fun default(sender: CommandSender) {
        val player = Bukkit.getOfflinePlayer(sender.name).player
        if (player == null) return
        sender.sendMessage {
            if (PreferenceService.isEnabled(player)) Component.text("Automatic item switching is on")
            else Component.text("Automatic item switching is off")
        }
    }

    @Subcommand("toggle")
    fun toggle(sender: CommandSender) {
        val player = Bukkit.getOfflinePlayer(sender.name).player
        if (player == null) return
        val result = PreferenceService.toggleEnabled(player)
        sender.sendMessage {
            if (result) Component.text("Toggled automatic item switching on")
            else Component.text("Toggled automatic item switching off")
        }
    }

    companion object {
        fun register() = ItemSwitch.commandManager.registerCommand(ItemSwitchCommand())
    }
}