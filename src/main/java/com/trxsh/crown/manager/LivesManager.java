package com.trxsh.crown.manager;

import com.sun.jna.platform.win32.Variant;
import com.trxsh.crown.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LivesManager {

    public Map<UUID, Integer> playerLives = new HashMap<UUID, Integer>();

    public int defaultLives = 5; // can be changed through config

    public LivesManager() {

        for(OfflinePlayer p : Bukkit.getOnlinePlayers()) {

            if(FileManager.lives.exists()) {

                playerLives = FileManager.readLives();

            }

            if(!playerLives.containsKey(p.getUniqueId())) {

                playerLives.put(p.getUniqueId(), defaultLives);

            }

        }

    }

    public void removeLife(Player p) {

        int lives = playerLives.get(p.getUniqueId());

        --lives;

        playerLives.replace(p.getUniqueId(), lives);

        if(lives <= 0) {

            playerLives.remove(p.getUniqueId());
            addNewPlayer(p.getUniqueId());

            Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " ran out of lives...");
            p.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "You ran out of lives...\n" + ChatColor.GREEN + "Get a friend to revive you!");
            Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "" + ChatColor.BOLD + "You ran out of lives...\n" + ChatColor.GREEN + "Get a friend to revive you!", null, "Console");
            DeathManager.addPlayer(p);

        }

    }

    public void addLife(Player p) {

        int lives = playerLives.get(p.getUniqueId());

        ++lives;

        playerLives.replace(p.getUniqueId(), lives);

    }

    public int removeLifes(Player p, int toTake) {

        int lives = playerLives.get(p.getUniqueId());

        lives = (lives - toTake);

        if(lives < 0 || lives == 0 || lives >= playerLives.get(p.getUniqueId())) {

            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, .5f);
            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + " You cannot remove all of your lives!");

            return 0;

        }

        playerLives.replace(p.getUniqueId(), lives);

        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, .5f);
        p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " Removed lives!");

        return toTake;

    }

    public void addNewPlayer(UUID id) {

        playerLives.put(id, defaultLives);

    }

    public void addNewPlayerWithSpecificLives(UUID id, int lives) {

        playerLives.put(id, lives);

    }

    public boolean isPlayerRegistered(UUID id) {

        for(UUID id1 : playerLives.keySet()) {

            if(id1.equals(id)) {

                return true;

            }

        }

        return false;

    }

    public int getPlayerLives(Player p) {

        return playerLives.get(p.getUniqueId());

    }

    public int getPlayerLives(UUID id) {

        return playerLives.get(id);

    }

}
