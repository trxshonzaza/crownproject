package com.trxsh.crown.executor;

import com.trxsh.crown.manager.TextureManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class TexturesExecutor implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equals("textures")) {

            if(sender instanceof Player) {

                try {

                    TextureManager.loadOptionalTexture((Player)sender);

                    ((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, .5f);
                    sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Textures will take a bit to load. Take your time!");

                    return true;

                } catch (InvocationTargetException e) { }

            } else {

                sender.sendMessage(ChatColor.RED + "Only a player can execute this command!");

            }

        }

        return false;

    }
}
