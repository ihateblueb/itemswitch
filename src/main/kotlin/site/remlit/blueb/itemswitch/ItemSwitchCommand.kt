package site.remlit.blueb.itemswitch

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

@Suppress("Unused")
@CommandAlias("itemswitch|is")
class ItemSwitchCommand : BaseCommand() {
    @Default
    fun default(sender: CommandSender) {
        val player = Bukkit.getOfflinePlayer(sender.name).player
        if (player == null) return
        sender.sendMessage(
            if (PreferenceService.isEnabled(player)) "Automatic item switching is off"
            else "Automatic item switching is off"
        )
    }

    @Subcommand("toggle")
    fun toggle(sender: CommandSender) = handle(sender)

    @Subcommand("toggle blockPlace")
    fun blockPlace(sender: CommandSender) = handle(sender, "blockPlace")

    @Subcommand("toggle itemBreak")
    fun itemBreak(sender: CommandSender) = handle(sender, "itemBreak")

    @Subcommand("toggle itemConsume")
    fun itemConsume(sender: CommandSender) = handle(sender, "itemConsume")

    @Subcommand("toggle bucketEmpty")
    fun bucketEmpty(sender: CommandSender) = handle(sender, "bucketEmpty")

    @Subcommand("toggle dropitem")
    fun dropItem(sender: CommandSender) = handle(sender, "dropItem")


    fun handle(sender: CommandSender, type: String? = null) {
        val player = Bukkit.getOfflinePlayer(sender.name).player
        if (player == null) return
        val result = if (type != null) PreferenceService.toggleEnabled(player, type) else PreferenceService.toggleEnabled(player)
        sender.sendMessage(
            if (result) "Toggled automatic item switching${if (type != null) " for $type " else " "}on"
            else "Toggled automatic item switching${if (type != null) " for $type " else " "}off"
        )
    }

    companion object {
        fun register() = ItemSwitch.commandManager.registerCommand(ItemSwitchCommand())
    }
}