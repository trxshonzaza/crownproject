package com.trxsh.crown.listener;

import com.trxsh.crown.data.PlayerData;
import com.trxsh.crown.manager.LivesManager;
import com.trxsh.crown.manager.PlayerManager;
import com.trxsh.crown.manager.TextureManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.InvocationTargetException;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        PlayerData.addPlayerData(e.getPlayer().getUniqueId());

        if(!LivesManager.instance.isPlayerRegistered(e.getPlayer())) {

            LivesManager.instance.addNewPlayer(e.getPlayer());

        }

        e.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You need to load the texture pack before playing to enjoy the full experience.");

        try {

            TextureManager.LoadTexture(e.getPlayer());

        }catch(InvocationTargetException e1) { }

    }

}
