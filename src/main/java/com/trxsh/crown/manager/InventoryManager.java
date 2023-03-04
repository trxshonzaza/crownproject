package com.trxsh.crown.manager;

import com.trxsh.crown.crafting.RecipeManager;
import com.trxsh.crown.data.PlayerData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class InventoryManager implements Listener {

    public static Inventory currentInv = null;

    public static Inventory open() {

        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.RED + "" + ChatColor.BOLD + "Respawn Players");

        for(OfflinePlayer p : DeathManager.deadPlayers.keySet()) {

            PlayerData data = PlayerData.getPlayerData(p.getUniqueId());

            ItemStack head = new ItemStack(Material.SKELETON_SKULL, 1);

            SkullMeta headMeta = (SkullMeta) head.getItemMeta();
            headMeta.setDisplayName(data.getName());
            headMeta.setLore(Arrays.asList("Died on " + Bukkit.getBanList(BanList.Type.NAME).getBanEntry(data.getName()).getCreated().toString()));

            head.setItemMeta(headMeta);

            inventory.addItem(head);

        }

        currentInv = inventory;
        return inventory;

    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {

        if(!e.getInventory().equals(currentInv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();

        unban(clickedItem, p);
        p.getInventory().removeItem(RecipeManager.beacon);
    }

    public static void unban(ItemStack item, Player p) {

        String name = item.getItemMeta().getDisplayName();

        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, .5f);
        p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + name + " has been unbanned!");

        currentInv.remove(item);
        DeathManager.removePlayerByName(name);

        Bukkit.getBanList(BanList.Type.NAME).pardon(name);

    }

}
