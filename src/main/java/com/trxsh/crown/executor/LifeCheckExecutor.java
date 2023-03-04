package com.trxsh.crown.executor;

import com.trxsh.crown.manager.InventoryManager;
import com.trxsh.crown.manager.LivesManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LifeCheckExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("lives")) {

            if(sender instanceof Player) {

                sender.sendMessage("You have " + ChatColor.BOLD + ChatColor.RED + ChatColor.BOLD + LivesManager.instance.getPlayerLives((Player)sender) + " life(s)");
                return true;

            } else {

                sender.sendMessage(ChatColor.RED + "A console cannot run this command!");
                return true;

            }

        }

        return false;

    }

}
