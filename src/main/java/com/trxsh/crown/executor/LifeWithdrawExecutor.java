package com.trxsh.crown.executor;

import com.trxsh.crown.crafting.RecipeManager;
import com.trxsh.crown.manager.LivesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class LifeWithdrawExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("withdraw")) {

            if(args[0] != null && args[0].length() > 0) {

                int toTake = Integer.parseInt(args[0]);

                if(sender instanceof Player) {

                    ItemStack toAdd = RecipeManager.extralife;

                    toAdd.setAmount(toTake);

                    ((Player) sender).getInventory().addItem(toAdd);

                    return LivesManager.instance.removeLifes(((Player) sender).getPlayer(), toTake);

                }

            }

        }

        return false;

    }

}
