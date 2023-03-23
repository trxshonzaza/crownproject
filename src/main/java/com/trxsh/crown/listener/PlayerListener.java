package com.trxsh.crown.listener;

import com.trxsh.crown.Main;
import com.trxsh.crown.data.PlayerData;
import com.trxsh.crown.manager.FileManager;
import com.trxsh.crown.manager.TextureManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.yaml.snakeyaml.Yaml;

import java.lang.reflect.InvocationTargetException;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        PlayerData.addPlayerData(e.getPlayer().getUniqueId());

        if(!Main.lifeManager.isPlayerRegistered(e.getPlayer().getUniqueId())) {

            if(FileManager.livesFileList.containsKey(e.getPlayer().getUniqueId())) {

                Main.lifeManager.playerLives.put(e.getPlayer().getUniqueId(), FileManager.livesFileList.get(e.getPlayer().getUniqueId()));

            } else {

                Main.lifeManager.addNewPlayer(e.getPlayer().getUniqueId());

            }

        }

        e.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You need to load the texture pack before playing to enjoy the full experience.");

        try {

            TextureManager.loadTexture(e.getPlayer());

        }catch(InvocationTargetException e1) { }

    }

}
