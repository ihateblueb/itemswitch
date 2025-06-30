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

    @Subcommand("toggle blockPlace")
    fun blockPlace(sender: CommandSender) {
        val player = Bukkit.getOfflinePlayer(sender.name).player
        if (player == null) return
        val result = PreferenceService.toggleEnabled(player, "blockPlace")
        sender.sendMessage {
            if (result) Component.text("Toggled automatic item switching for blockPlace on")
            else Component.text("Toggled automatic item switching for blockPlace off")
        }
    }

    @Subcommand("toggle itemBreak")
    fun itemBreak(sender: CommandSender) {
        val player = Bukkit.getOfflinePlayer(sender.name).player
        if (player == null) return
        val result = PreferenceService.toggleEnabled(player, "itemBreak")
        sender.sendMessage {
            if (result) Component.text("Toggled automatic item switching for itemBreak on")
            else Component.text("Toggled automatic item switching for itemBreak off")
        }
    }

    @Subcommand("toggle itemConsume")
    fun itemConsume(sender: CommandSender) {
        val player = Bukkit.getOfflinePlayer(sender.name).player
        if (player == null) return
        val result = PreferenceService.toggleEnabled(player, "itemConsume")
        sender.sendMessage {
            if (result) Component.text("Toggled automatic item switching for itemConsume on")
            else Component.text("Toggled automatic item switching for itemConsume off")
        }
    }

    @Subcommand("toggle bucketEmpty")
    fun bucketEmpty(sender: CommandSender) {
        val player = Bukkit.getOfflinePlayer(sender.name).player
        if (player == null) return
        val result = PreferenceService.toggleEnabled(player, "bucketEmpty")
        sender.sendMessage {
            if (result) Component.text("Toggled automatic item switching for bucketEmpty on")
            else Component.text("Toggled automatic item switching for bucketEmpty off")
        }
    }

    @Subcommand("toggle dropItem")
    fun dropItem(sender: CommandSender) {
        val player = Bukkit.getOfflinePlayer(sender.name).player
        if (player == null) return
        val result = PreferenceService.toggleEnabled(player, "dropItem")
        sender.sendMessage {
            if (result) Component.text("Toggled automatic item switching for dropItem on")
            else Component.text("Toggled automatic item switching for dropItem off")
        }
    }

    companion object {
        fun register() = ItemSwitch.commandManager.registerCommand(ItemSwitchCommand())
    }
}