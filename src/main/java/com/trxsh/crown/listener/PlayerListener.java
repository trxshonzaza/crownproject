package com.trxsh.crown.listener;

import com.trxsh.crown.data.PlayerData;
import com.trxsh.crown.manager.LivesManager;
import com.trxsh.crown.manager.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        PlayerData.addPlayerData(e.getPlayer().getUniqueId());

        if(!LivesManager.instance.isPlayerRegistered(e.getPlayer())) {

            LivesManager.instance.addNewPlayer(e.getPlayer());

        }

    }

}
