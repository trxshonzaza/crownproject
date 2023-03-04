package com.trxsh.crown.manager;

import com.trxsh.crown.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerManager {

    public static final ArrayList<Player> crownedPlayers = new ArrayList<Player>();
    public static final ArrayList<Player> potionedPlayers = new ArrayList<Player>();

    public static void addCrownedPlayer(Player player) {

        crownedPlayers.add(player);
        //Bukkit.broadcastMessage(player.getName() + " is now a crowned player");

    }

    public static void removeCrownedPlayer(Player player) {

        crownedPlayers.remove(player);
        //Bukkit.broadcastMessage(player.getName() + " is no longer a crowned player");

    }

    public static void addPotionedPlayer(Player player) {

        potionedPlayers.add(player);
        //Bukkit.broadcastMessage(player.getName() + " is now a potioned player");

    }

    public static void removePotionedPlayer(Player player) {

        potionedPlayers.remove(player);
        //Bukkit.broadcastMessage(player.getName() + " is no longer a crowned player");

    }

    public static boolean hasCrownedPlayer(Player player) {

        if(crownedPlayers.contains(player)) {

            return true;

        }

        return false;
    }

    public static boolean hasPotionedPlayer(Player player) {

        if(potionedPlayers.contains(player)) {

            return true;

        }

        return false;
    }

}
