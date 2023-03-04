package com.trxsh.crown.executor;

import com.trxsh.crown.manager.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("lists")) {

            if(sender instanceof Player) {

                if(!sender.isOp()) {

                    sender.sendMessage(ChatColor.RED + "you are not operator!");
                    return true;

                }

            }

            for(Player player : PlayerManager.crownedPlayers) {

                sender.sendMessage(player.getName() + " is a crowned player");

            }

            for(Player player : PlayerManager.potionedPlayers) {

                sender.sendMessage(player.getName() + " is a potioned player");

            }

            return true;
        }

        return false;

    }

}
