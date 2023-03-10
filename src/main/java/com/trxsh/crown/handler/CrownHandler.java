package com.trxsh.crown.handler;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CrownHandler {

    public static ItemStack crownStack;
    public static int droppedCrowns = 0;

    public static List<Player> whoIsHoldingCrownList() {

        List<Player> toReturn = new ArrayList<Player>();

        for(Player player : Bukkit.getOnlinePlayers()) {

            if(player.getInventory().getHelmet() != null) {

                if(player.getInventory().getHelmet().equals(crownStack)) {

                    toReturn.add(player);

                }

            }

        }

        return toReturn;

    }

    public static void dropCrown(Location location, Player killer) {

        killer.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
        killer.spawnParticle(Particle.EXPLOSION_HUGE, location, 2);

        World world = killer.getWorld();

        if(droppedCrowns < 3) {

            Item stack = world.dropItem(location, getCrownStack());

            killer.sendMessage(ChatColor.BOLD + "Whats this? " + ChatColor.WHITE + "a " + ChatColor.YELLOW + "" + ChatColor.BOLD + "CROWN" + ChatColor.WHITE + " appears!");

            ++droppedCrowns;

            return;

        }

    }

    public static ItemStack getCrownStack() {

        ItemStack stack = new ItemStack(Material.GOLDEN_HELMET);

        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Crown");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.DURABILITY, 10, true);

        stack.setItemMeta(meta);

        crownStack = stack;

        return stack;

    }
}
