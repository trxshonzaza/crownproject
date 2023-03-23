package com.trxsh.crown.executor;

import com.trxsh.crown.Main;
import com.trxsh.crown.manager.InventoryManager;
import com.trxsh.crown.manager.LivesManager;
import org.bukkit.Bukkit;
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

                if(args.length > 0) {

                    if(sender.isOp()) {

                        Player player = Bukkit.getPlayer(args[0]);

                        sender.sendMessage(player.getName() + " has " + ChatColor.BOLD + ChatColor.RED + ChatColor.BOLD + Main.lifeManager.getPlayerLives(player) + " life(s)");
                        return true;

                    } else {

                        sender.sendMessage(ChatColor.RED + "You are not operator!");
                        return true;

                    }

                } else {

                    sender.sendMessage("You have " + ChatColor.BOLD + ChatColor.RED + ChatColor.BOLD + Main.lifeManager.getPlayerLives((Player)sender) + " life(s)");
                    return true;

                }

            } else {

                sender.sendMessage(ChatColor.RED + "A console cannot run this command!");
                return true;

            }

        }

        return false;

    }

}
