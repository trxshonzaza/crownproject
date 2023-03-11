package com.trxsh.crown.manager;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class LivesManager {

    public static Map<Player, Integer> playerLives = new HashMap<Player, Integer>();
    public static LivesManager instance = null;

    public int defaultLives = 5; // can be changed through config

    public LivesManager() {

        for(Player p : Bukkit.getOnlinePlayers()) {

            if(FileManager.lives.exists()) {

                FileManager.readLives();

            }

            if(!playerLives.containsKey(p)) {

                playerLives.put(p, defaultLives);

            }

        }

        instance = this;

    }

    public LivesManager(int defaultLives) {

        this.defaultLives = defaultLives;

        for(Player p : Bukkit.getOnlinePlayers()) {

            if(!playerLives.containsKey(p)) {

                playerLives.put(p, this.defaultLives);

            }

        }

        instance = this;

    }

    public void removeLife(Player p) {

        int lives = playerLives.get(p);

        --lives;

        playerLives.replace(p, lives);

        if(lives <= 0) {

            playerLives.remove(p);
            addNewPlayer(p);

            p.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "You ran out of lives...\n" + ChatColor.GREEN + "Get a friend to revive you!");
            Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "" + ChatColor.BOLD + "You ran out of lives...\n" + ChatColor.GREEN + "Get a friend to revive you!", null, "Console");
            DeathManager.addPlayer(p);

        }

    }

    public void addLife(Player p) {

        int lives = playerLives.get(p);

        ++lives;

        playerLives.replace(p, lives);

    }

    public int removeLifes(Player p, int toTake) {

        int lives = playerLives.get(p);

        lives = (lives - toTake);

        if(lives < 0 || lives == 0 || lives >= playerLives.get(p)) {

            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, .5f);
            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + " You cannot remove all of your lives!");

            return 0;

        }

        playerLives.replace(p, lives);

        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, .5f);
        p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " Removed lives!");

        return toTake;

    }

    public void addNewPlayer(Player p) {

        playerLives.put(p, defaultLives);

    }

    public boolean isPlayerRegistered(Player p) {

        if(playerLives.containsKey(p)) {

            return true;

        }

        return false;

    }

    public int getPlayerLives(Player p) {

        return playerLives.get(p);

    }

}
