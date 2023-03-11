package com.trxsh.crown.listener;

import com.trxsh.crown.crafting.RecipeManager;
import com.trxsh.crown.manager.InventoryManager;
import com.trxsh.crown.manager.LivesManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemUseListener implements Listener {

    @EventHandler
    public void onItemUse(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(e.getItem() != null) {

                if(e.getItem().isSimilar(RecipeManager.beacon)) {

                    e.getPlayer().openInventory(InventoryManager.open());

                }

                if(e.getItem().isSimilar(RecipeManager.extralife)) {

                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, .5f);

                    e.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You gained an extra life!");

                    LivesManager.instance.addLife(e.getPlayer());

                    if(e.getItem().getAmount() == 1) {

                        e.getItem().setAmount(0);

                    } else {

                        e.getItem().setAmount(e.getItem().getAmount() - 1);

                    }

                }

            }

        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        if(e.getItemInHand() != null) {

            if(e.getItemInHand().isSimilar(RecipeManager.beacon)) {

                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot place a respawn beacon!");

            }

        }

    }

}
