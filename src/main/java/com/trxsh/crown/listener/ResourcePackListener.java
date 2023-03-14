package com.trxsh.crown.listener;

import com.trxsh.crown.Main;
import com.trxsh.crown.manager.TextureManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class ResourcePackListener implements Listener {

    @EventHandler
    public void onResourcePackStatus(PlayerResourcePackStatusEvent e) {

        if(!TextureManager.loadingOptional.contains(e.getPlayer())) {

            if(e.getStatus().equals(PlayerResourcePackStatusEvent.Status.DECLINED)) {

                e.getPlayer().kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "You must accept and download the resource pack to play on the server! \n" + ChatColor.GREEN + "" + ChatColor.BOLD + "need help? create a ticket in '#tickets'");

            }

            if(e.getStatus().equals(PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD)) {

                e.getPlayer().kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "You must accept and download the resource pack to play on the server! \n" + ChatColor.GREEN + "" + ChatColor.BOLD + "need help? create a ticket in '#tickets'");

            }

            if(e.getStatus().equals(PlayerResourcePackStatusEvent.Status.ACCEPTED)) {

                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, .5f);
                e.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Thank you for loading the texture pack! have fun!");

            }

        }

        if(TextureManager.loadingOptional.contains(e.getPlayer())) {

            TextureManager.loadingOptional.remove(e.getPlayer());

        }

    }

}
