package com.trxsh.crown.crafting;

import com.trxsh.crown.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class RecipeManager {

    public static ItemStack beacon;
    public static ItemStack extralife;

    public static void registerRecipes() {

        getBeacon();
        getExtraLife();

        /* Respawn Beacon Recipe */
        NamespacedKey key = new NamespacedKey(Main.instance, "respawn_beacon");

        ShapedRecipe recipe = new ShapedRecipe(key, beacon);

        recipe.shape("DND", "OEO", "DND");

        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_BLOCK);
        recipe.setIngredient('O', Material.OBSIDIAN);
        recipe.setIngredient('E', Material.ECHO_SHARD);

        NamespacedKey key1 = new NamespacedKey(Main.instance, "extra_life");

        ShapedRecipe recipe1 = new ShapedRecipe(key1, extralife);

        recipe1.shape("NDN", "DRD", "NDN");

        recipe1.setIngredient('N', Material.NETHERITE_BLOCK);
        recipe1.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe1.setIngredient('R', Material.REDSTONE_BLOCK);

        Bukkit.addRecipe(recipe);
        Bukkit.addRecipe(recipe1);
    }

    public static ItemStack getBeacon() {

        ItemStack stack = new ItemStack(Material.BEACON, 1);

        ItemMeta meta = stack.getItemMeta();

        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Respawn Beacon");
        meta.setUnbreakable(true);
        meta.setLore(Arrays.asList("Respawn a friend!"));

        stack.setItemMeta(meta);

        beacon = stack;

        return stack;

    }

    public static ItemStack getExtraLife() {

        ItemStack stack = new ItemStack(Material.ECHO_SHARD, 1);

        ItemMeta meta = stack.getItemMeta();

        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Extra Life");
        meta.setUnbreakable(true);
        meta.setLore(Arrays.asList("Gain Another Life!"));

        stack.setItemMeta(meta);

        extralife = stack;

        return stack;

    }

}
