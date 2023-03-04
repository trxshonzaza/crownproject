package com.trxsh.crown.manager;

import com.trxsh.crown.data.PlayerData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeathManager {

    public static Map<OfflinePlayer, PlayerData> deadPlayers = new HashMap<OfflinePlayer, PlayerData>();

    public static void addPlayer(Player p) {

        deadPlayers.put(p, PlayerData.getPlayerData(p.getUniqueId()));

    }

    public static void removePlayer(Player p) {

        deadPlayers.remove(p);

    }

    public static void removePlayerByName(String name) {

        for(OfflinePlayer p : deadPlayers.keySet()) {

            if(p.getName().equals(name)) {

                deadPlayers.remove(p);
                return;

            }

        }

    }

}
